package org.tokend.sdk.keyserver

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.spongycastle.util.encoders.Base64
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.kdf.ScryptWithMasterKeyDerivation
import org.tokend.sdk.api.ApiFactory
import org.tokend.sdk.api.models.EncryptedKey
import org.tokend.sdk.api.models.KeychainData
import org.tokend.sdk.api.models.WalletData
import org.tokend.sdk.api.models.WalletRelation
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.federation.EmailAlreadyTakenException
import org.tokend.sdk.federation.EmailNotVerifiedException
import org.tokend.sdk.federation.InvalidCredentialsException
import org.tokend.sdk.keyserver.models.KdfAttributes
import org.tokend.sdk.keyserver.models.LoginParamsResponse
import org.tokend.sdk.keyserver.models.WalletInfo
import org.tokend.sdk.utils.extentions.encodeHex
import org.tokend.sdk.utils.extentions.toBytes
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.security.SecureRandom

class KeyStorage(keyServerUrl: String, cookieJarProvider: CookieJarProvider? = null) {
    private val keyServerApiService = KeyServerApiFactory.getApiService(keyServerUrl, cookieJarProvider)

    // region Obtain
    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class
    )
    fun getWalletInfo(login: String, password: String, isRecovery: Boolean = false): WalletInfo {
        val loginParams = getApiLoginParams(login, isRecovery)

        val tempLogin = if (loginParams.id == 2L) login.toLowerCase() else login

        val hexWalletId = getWalletIdHex(tempLogin, password, loginParams.kdfAttributes)
        val walletKey = getWalletKey(tempLogin, password, loginParams.kdfAttributes)

        val walletData = getWalletData(hexWalletId)

        val iv = walletData.attributes?.iv
                ?: throw IllegalStateException("Wallet data has no IV")
        val cipherText = walletData.attributes?.cipherText
                ?: throw IllegalStateException("Wallet data has no encrypted seed")
        val accountId = walletData.attributes?.accountId
                ?: throw IllegalStateException("Wallet data has no account ID")
        val email = walletData.attributes?.email
                ?: throw IllegalStateException("Wallet data has no email")

        return WalletInfo(accountId, email, hexWalletId, decryptSecretSeed(iv, cipherText, walletKey))
    }

    @Throws(InvalidCredentialsException::class, HttpException::class)
    fun getApiLoginParams(login: String? = null, isRecovery: Boolean = false): LoginParamsResponse {
        val response = keyServerApiService.getLoginParams(login, isRecovery).execute()
        val data = response.body()?.data

        if (data != null) {
            return data
        } else {
            when (response.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> throw InvalidCredentialsException()
                else -> throw HttpException(response)
            }
        }
    }

    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class)
    fun getWalletData(walletId: String): WalletData {
        val response = keyServerApiService.getWalletData(walletId).execute()
        val data = response.body()?.data

        if (data != null) {
            return data
        } else {
            when (response.code()) {
                HttpURLConnection.HTTP_FORBIDDEN -> throw EmailNotVerifiedException(walletId)
                HttpURLConnection.HTTP_NOT_FOUND -> throw InvalidCredentialsException()
                else -> throw HttpException(response)
            }
        }
    }
    // endregion

    // region Create
    fun createBaseWallet(
            email: String,
            salt: ByteArray,
            walletIdHex: String,
            walletKey: ByteArray,
            seed: String,
            accountId: String,
            kdfVersion: Long): WalletData {
        val encryptedRoot =
                encryptWalletKey(email, seed, accountId, walletKey, salt)

        val relations = listOf(
                WalletRelation(WalletRelation.RELATION_KDF, WalletRelation.RELATION_KDF,
                        kdfVersion.toString())
        )

        return WalletData(walletIdHex, encryptedRoot, relations)
    }
    // endregion
    fun saveWallet(walletData: WalletData) {
        val response = keyServerApiService.createWallet(DataEntity(walletData)).execute()
        val success = response.isSuccessful

        if (!success) {
            when (response.code()) {
                HttpURLConnection.HTTP_CONFLICT -> throw EmailAlreadyTakenException()
                else -> throw HttpException(response)
            }
        }
    }
    // region Save

    companion object {
        private const val WALLET_ID_MASTER_KEY = "WALLET_ID"
        private const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"
        private const val IV_LENGTH = 12

        fun getWalletKey(login: String, password: String, kdfAttributes: KdfAttributes): ByteArray {
            return deriveWallet(login.toByteArray(), password.toByteArray(),
                    WALLET_KEY_MASTER_KEY.toByteArray(), kdfAttributes)
        }

        private fun deriveWallet(login: ByteArray, password: ByteArray, masterKey: ByteArray, kdfAttributes: KdfAttributes): ByteArray {
            val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n, kdfAttributes.r,
                    kdfAttributes.p, login, masterKey)
            return derivation.derive(password, kdfAttributes.salt, kdfAttributes.bits.toBytes())
        }

        private fun decryptSecretSeed(iv: ByteArray, cipherText: ByteArray, key: ByteArray): String {
            val privateSeedJson = String(Aes256GCM(iv).decrypt(cipherText, key))
            return JsonParser().parse(privateSeedJson).asJsonObject["seed"].asString
        }

        private fun encryptSecretSeed(seed: String, iv: ByteArray, key: ByteArray): ByteArray {
            val seedJson = JsonObject()
            seedJson.addProperty("seed", seed)
            return Aes256GCM(iv).encrypt(seedJson.toString().toByteArray(), key)
        }

        fun getWalletIdHex(login: String, password: String, kdfAttributes: KdfAttributes): String {
            return String(deriveWallet(login.toByteArray(), password.toByteArray(),
                    WALLET_ID_MASTER_KEY.toByteArray(), kdfAttributes).encodeHex())
        }

        fun encryptWalletKey(email: String, seed: String, accountId: String,
                             encryptionKey: ByteArray, keyDerivationSalt: ByteArray)
                : EncryptedKey {
            val iv = SecureRandom.getSeed(IV_LENGTH)
            val encryptedSeed = encryptSecretSeed(seed, iv, encryptionKey)
            val keychainData = KeychainData(
                    Base64.toBase64String(iv),
                    Base64.toBase64String(encryptedSeed)
            )
            val keychainDataJson = ApiFactory.getBaseGson().toJson(keychainData)

            return EncryptedKey(
                    accountId,
                    email,
                    Base64.toBase64String(keyDerivationSalt),
                    Base64.toBase64String(keychainDataJson.toByteArray())
            )
        }
    }
}