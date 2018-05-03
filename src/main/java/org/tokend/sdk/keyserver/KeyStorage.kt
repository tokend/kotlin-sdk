package org.tokend.sdk.keyserver

import com.google.gson.JsonParser
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.kdf.ScryptWithMasterKeyDerivation
import org.tokend.sdk.api.models.WalletData
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.federation.EmailNotVerifiedException
import org.tokend.sdk.federation.InvalidCredentialsException
import org.tokend.sdk.keyserver.models.KdfAttributes
import org.tokend.sdk.keyserver.models.LoginParamsResponse
import org.tokend.sdk.keyserver.models.WalletInfo
import org.tokend.sdk.utils.extentions.encodeHex
import org.tokend.sdk.utils.extentions.toBytes
import retrofit2.HttpException
import java.net.HttpURLConnection

class KeyStorage(keyServerUrl: String, cookieJarProvider: CookieJarProvider? = null) {
    private val keyServerApiService = KeyServerApiFactory.getApiService(keyServerUrl, cookieJarProvider)

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

        return WalletInfo(accountId, email, hexWalletId, getSecretSeed(iv, cipherText, walletKey))
    }

    @Throws(InvalidCredentialsException::class, HttpException::class)
    fun getApiLoginParams(login: String, isRecovery: Boolean): LoginParamsResponse {
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
                HttpURLConnection.HTTP_FORBIDDEN -> throw EmailNotVerifiedException()
                HttpURLConnection.HTTP_NOT_FOUND -> throw InvalidCredentialsException()
                else -> throw HttpException(response)
            }
        }
    }

    private companion object {
        const val WALLET_ID_MASTER_KEY = "WALLET_ID"
        const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"

        fun getWalletKey(login: String, password: String, kdfAttributes: KdfAttributes): ByteArray {
            return deriveWallet(login.toByteArray(), password.toByteArray(),
                    WALLET_KEY_MASTER_KEY.toByteArray(), kdfAttributes)
        }

        fun deriveWallet(login: ByteArray, password: ByteArray, masterKey: ByteArray, kdfAttributes: KdfAttributes): ByteArray {
            val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n, kdfAttributes.r,
                    kdfAttributes.p, login, masterKey)
            return derivation.derive(password, kdfAttributes.salt!!, kdfAttributes.bits.toBytes())
        }

        fun getSecretSeed(iv: ByteArray, cipherText: ByteArray, key: ByteArray): String {
            val privateSeedJson = String(Aes256GCM(iv).decrypt(cipherText, key))
            return JsonParser().parse(privateSeedJson).asJsonObject["seed"].asString
        }

        fun getWalletIdHex(login: String, password: String, kdfAttributes: KdfAttributes): String {
            return String(deriveWallet(login.toByteArray(), password.toByteArray(),
                    WALLET_ID_MASTER_KEY.toByteArray(), kdfAttributes).encodeHex())
        }
    }
}