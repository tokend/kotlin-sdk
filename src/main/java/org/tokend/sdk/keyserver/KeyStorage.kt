package org.tokend.sdk.keyserver

import org.spongycastle.util.encoders.Base64
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.kdf.ScryptWithMasterKeyDerivation
import org.tokend.sdk.api.models.EncryptedKey
import org.tokend.sdk.api.models.KeychainData
import org.tokend.sdk.api.models.WalletData
import org.tokend.sdk.api.models.WalletRelation
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.api.requests.RequestSigner
import org.tokend.sdk.api.tfa.model.TfaCallback
import org.tokend.sdk.api.base.factory.ServiceFactory
import org.tokend.sdk.api.base.factory.GsonFactory
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
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.security.SecureRandom

class KeyStorage @JvmOverloads constructor(
        keyServerUrl: String,
        tfaCallback: TfaCallback? = null,
        cookieJarProvider: CookieJarProvider? = null,
        requestSigner: RequestSigner? = null,
        userAgent: String? = null
) {
    private val keyServerApiService: KeyServerApi =
            ServiceFactory(keyServerUrl, userAgent)
                    .getKeyServerApi(requestSigner, tfaCallback, cookieJarProvider)

    // region Obtain
    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class
    )
    fun getWalletInfo(login: String, password: CharArray,
                      isRecovery: Boolean = false): WalletInfo {
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

        if (isRecovery) {
            return WalletInfo(accountId, email, hexWalletId, CharArray(0), loginParams)
        } else {
            return WalletInfo(accountId, email, hexWalletId,
                    decryptSecretSeed(iv, cipherText, walletKey), loginParams)
        }
    }

    @Throws(InvalidCredentialsException::class, HttpException::class)
    fun getApiLoginParams(login: String? = null, isRecovery: Boolean = false): LoginParamsResponse {
        val response = keyServerApiService.getLoginParams(login, isRecovery).execute()
        val data = response.body()?.data

        if (data != null) {
            return data
        } else {
            when (response.code()) {
                HttpURLConnection.HTTP_NOT_FOUND -> throw InvalidCredentialsException(
                        InvalidCredentialsException.Credential.EMAIL
                )
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
                HttpURLConnection.HTTP_NOT_FOUND -> throw InvalidCredentialsException(
                        InvalidCredentialsException.Credential.PASSWORD
                )
                else -> throw HttpException(response)
            }
        }
    }
    // endregion

    // endregion

    // region Save
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

    fun updateWallet(walletId: String, walletData: WalletData) {
        val response = keyServerApiService.updateWallet(walletId, DataEntity(walletData)).execute()
        val success = response.isSuccessful

        if (!success) {
            throw HttpException(response)
        }
    }
    // endregion

    companion object {
        private const val WALLET_ID_MASTER_KEY = "WALLET_ID"
        private const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"
        private const val IV_LENGTH = 12

        fun getWalletKey(login: String, password: CharArray,
                         kdfAttributes: KdfAttributes): ByteArray {
            val passwordCharBuffer = CharBuffer.wrap(password)
            val passwordByteBuffer = Charset.defaultCharset().encode(passwordCharBuffer)
            passwordCharBuffer.clear()

            val passwordBytes = ByteArray(passwordByteBuffer.remaining())
            passwordByteBuffer.get(passwordBytes).clear()

            val result = deriveWallet(login.toByteArray(), passwordBytes,
                    WALLET_KEY_MASTER_KEY.toByteArray(), kdfAttributes)
            passwordBytes.fill(0)

            return result
        }

        private fun deriveWallet(login: ByteArray, password: ByteArray, masterKey: ByteArray, kdfAttributes: KdfAttributes): ByteArray {
            val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n, kdfAttributes.r,
                    kdfAttributes.p, login, masterKey)
            return derivation.derive(password, kdfAttributes.salt, kdfAttributes.bits.toBytes())
        }

        fun decryptSecretSeed(iv: ByteArray, cipherText: ByteArray, key: ByteArray): CharArray {
            val seedJsonBytes = Aes256GCM(iv).decrypt(cipherText, key)
            val seedJsonByteBuffer = ByteBuffer.wrap(seedJsonBytes)

            val seedJsonCharBuffer = Charset.defaultCharset().decode(seedJsonByteBuffer)
            seedJsonByteBuffer.clear()
            seedJsonBytes.fill(0)
            val seedJsonChars = CharArray(seedJsonCharBuffer.remaining())
            seedJsonCharBuffer.get(seedJsonChars).clear()

            val seedStartKey = "\"seed\"".toCharArray()
            var seedStartIndex = seedJsonChars.foldIndexed(-1) { index, found, _ ->
                if (found >= 0) {
                    return@foldIndexed found
                }

                var match = true
                for (i in 0 until seedStartKey.size) {
                    if (i + index == seedJsonChars.size) {
                        return@foldIndexed -1
                    }
                    if (seedStartKey[i] != seedJsonChars[i + index]) {
                        match = false
                        break
                    }
                }
                return@foldIndexed if (match) index else -1
            }
            seedJsonChars.fill('0', 0, seedStartIndex + seedStartKey.size)
            seedStartIndex = seedJsonChars.indexOf('"')
            seedJsonChars[seedStartIndex] = '0'

            seedStartIndex++
            val seedEndIndex = seedJsonChars.indexOf('"')
            val seed = seedJsonChars.copyOfRange(seedStartIndex, seedEndIndex)
            seedJsonChars.fill('0')

            return seed
        }

        private fun encryptSecretSeed(seed: CharArray, iv: ByteArray, key: ByteArray): ByteArray {
            val jsonStartChars = "{\"seed\":\"".toCharArray()
            val jsonEndChars = "\"}".toCharArray()
            val jsonChars = jsonStartChars.plus(seed).plus(jsonEndChars)

            val jsonCharBuffer = CharBuffer.wrap(jsonChars)
            val jsonByteBuffer = Charset.defaultCharset().encode(jsonCharBuffer)
            jsonCharBuffer.clear()
            jsonChars.fill('0')

            val jsonBytes = ByteArray(jsonByteBuffer.remaining())
            jsonByteBuffer.get(jsonBytes).clear()

            val encrypted = Aes256GCM(iv).encrypt(jsonBytes, key)
            jsonBytes.fill(0)

            return encrypted
        }

        fun getWalletIdHex(login: String, password: CharArray,
                           kdfAttributes: KdfAttributes): String {
            val passwordCharBuffer = CharBuffer.wrap(password)
            val passwordByteBuffer = Charset.defaultCharset().encode(passwordCharBuffer)
            passwordCharBuffer.clear()

            val passwordBytes = ByteArray(passwordByteBuffer.remaining())
            passwordByteBuffer.get(passwordBytes).clear()

            val result = String(deriveWallet(login.toByteArray(), passwordBytes,
                    WALLET_ID_MASTER_KEY.toByteArray(), kdfAttributes).encodeHex())
            passwordBytes.fill(0)

            return result
        }

        fun encryptWalletKey(email: String, seed: CharArray, accountId: String,
                             encryptionKey: ByteArray, keyDerivationSalt: ByteArray)
                : EncryptedKey {
            val iv = SecureRandom.getSeed(IV_LENGTH)
            val encryptedSeed = encryptSecretSeed(seed, iv, encryptionKey)
            val keychainData = KeychainData(
                    Base64.toBase64String(iv),
                    Base64.toBase64String(encryptedSeed)
            )
            val keychainDataJson = GsonFactory().getBaseGson().toJson(keychainData)

            return EncryptedKey(
                    accountId,
                    email,
                    Base64.toBase64String(keyDerivationSalt),
                    Base64.toBase64String(keychainDataJson.toByteArray())
            )
        }

        // region Create
        fun createBaseWallet(
                email: String,
                salt: ByteArray,
                walletIdHex: String,
                walletKey: ByteArray,
                seed: CharArray,
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
    }

}