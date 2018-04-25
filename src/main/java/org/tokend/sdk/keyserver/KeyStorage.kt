package org.tokend.sdk.keyserver

import com.google.gson.JsonParser
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.kdf.ScryptWithMasterKeyDerivation
import org.tokend.sdk.api.models.WalletData
import org.tokend.sdk.api.requests.CookieJarProvider
import org.tokend.sdk.federation.NotFoundException
import org.tokend.sdk.keyserver.models.KdfAttributes
import org.tokend.sdk.keyserver.models.LoginParamsResponse
import org.tokend.sdk.keyserver.models.WalletInfo
import org.tokend.sdk.utils.extentions.encodeHex
import org.tokend.sdk.utils.extentions.toBytes

class KeyStorage(keyServerUrl: String, cookieJarProvider: CookieJarProvider? = null) {
    private val keyServerApiService = KeyServerApiFactory.getApiService(keyServerUrl, cookieJarProvider)

    fun getWalletInfo(login: String, password: String, isRecovery: Boolean = false): WalletInfo {
        try {
            val loginParams = getApiLoginParams(login, isRecovery)!!
            val tempLogin = if (loginParams.id == 2L) login.toLowerCase() else login

            val hexWalletId = getWalletIdHex(tempLogin, password, loginParams.kdfAttributes!!)
            val walletKey = getWalletKey(tempLogin, password, loginParams.kdfAttributes!!)

            val walletData = getWalletData(hexWalletId)!!
            val iv = walletData.attributes?.iv!!
            val cipherText = walletData.attributes?.cipherText!!

            return WalletInfo(walletData.attributes?.accountId!!, hexWalletId, getSecretSeed(iv, cipherText, walletKey))
        } catch (e: Throwable) {
            when (e) {
                is NullPointerException,
                is NotFoundException -> throw NotFoundException("Unable to obtain salt")
                else -> throw e
            }
        }
    }

    private fun getApiLoginParams(login: String, isRecovery: Boolean): LoginParamsResponse? {
        return keyServerApiService.getLoginParams(login, isRecovery).execute().body()?.data
    }

    private fun getWalletData(walletId: String): WalletData? {
        return keyServerApiService.getWalletData(walletId).execute().body()?.data
    }

    private companion object {
        const val WALLET_ID_MASTER_KEY = "WALLET_ID"
        const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"

        fun getWalletKey(login: String, password: String, kdfAttributes: KdfAttributes): ByteArray {
            return deriveWallet(login.toByteArray(), password.toByteArray(),
                    WALLET_KEY_MASTER_KEY.toByteArray(), kdfAttributes)
        }

        fun deriveWallet(login: ByteArray, password: ByteArray, masterKey: ByteArray, kdfAttributes: KdfAttributes): ByteArray {
            val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n!!, kdfAttributes.r!!,
                    kdfAttributes.p!!, login, masterKey)
            return derivation.derive(password, kdfAttributes.salt!!, kdfAttributes.bits!!.toBytes())
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