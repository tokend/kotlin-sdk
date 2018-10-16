package org.tokend.sdk.keyserver

import org.spongycastle.util.encoders.Base64
import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.kdf.ScryptWithMasterKeyDerivation
import org.tokend.sdk.api.wallets.WalletsApi
import org.tokend.sdk.api.wallets.model.EmailAlreadyTakenException
import org.tokend.sdk.api.wallets.model.EmailNotVerifiedException
import org.tokend.sdk.api.wallets.model.InvalidCredentialsException
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.keyserver.models.*
import org.tokend.sdk.utils.extentions.encodeHex
import org.tokend.sdk.utils.extentions.toBytes
import retrofit2.HttpException
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.security.SecureRandom

class KeyStorage constructor(
        private val walletsApi: WalletsApi
) {
    // region Obtain
    /**
     * Loads user's wallet and decrypts secret seed.
     */
    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class
    )
    fun getWalletInfo(login: String, password: CharArray,
                      isRecovery: Boolean = false): WalletInfo {
        val loginParams = getLoginParams(login, isRecovery)

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

    /**
     * Loads KDF params.
     */
    @Throws(InvalidCredentialsException::class, HttpException::class)
    fun getLoginParams(login: String? = null, isRecovery: Boolean = false): LoginParams {
        val response = walletsApi.getLoginParams(login, isRecovery).execute()
        return response.get()
    }

    /**
     * Loads wallet by wallet ID.
     */
    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class)
    fun getWalletData(walletId: String): WalletData {
        val response = walletsApi.getById(walletId).execute()
        return response.get()
    }
    // endregion

    // region Save
    /**
     * Submits given wallet to the system.
     */
    @Throws(
            EmailAlreadyTakenException::class,
            HttpException::class
    )
    fun saveWallet(walletData: WalletData) {
        walletsApi.create(walletData).execute()
    }

    /**
     * Updates wallet by given wallet ID with given data.
     */
    fun updateWallet(walletId: String, walletData: WalletData) {
        walletsApi.update(walletId, walletData).execute()
    }
    // endregion

    companion object {
        private const val WALLET_ID_MASTER_KEY = "WALLET_ID"
        private const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"
        private const val IV_LENGTH = 12

        /**
         * @return key for secret seed encryption/decryption
         */
        fun getWalletKey(login: String, password: CharArray,
                         kdfAttributes: KdfAttributes): ByteArray {
            val passwordCharBuffer = CharBuffer.wrap(password)
            val passwordByteBuffer = Charset.defaultCharset().encode(passwordCharBuffer)
            passwordCharBuffer.clear()

            val passwordBytes = ByteArray(passwordByteBuffer.remaining())
            passwordByteBuffer.get(passwordBytes).clear()

            val result = deriveKey(login.toByteArray(), passwordBytes,
                    WALLET_KEY_MASTER_KEY.toByteArray(), kdfAttributes)
            passwordBytes.fill(0)

            return result
        }

        private fun deriveKey(login: ByteArray,
                              password: ByteArray,
                              masterKey: ByteArray,
                              kdfAttributes: KdfAttributes): ByteArray {
            val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n, kdfAttributes.r,
                    kdfAttributes.p, login, masterKey)
            val salt = kdfAttributes.salt
                    ?: throw IllegalArgumentException("KDF salt is required for derivation")
            return derivation.derive(password, salt, kdfAttributes.bits.toBytes())
        }

        /**
         * Decrypts secret seed with given key.
         * @param iv cipher init vector
         * @param cipherText encrypted secret seed
         * @param key decryption key
         *
         * @see KeychainData
         * @see KeyStorage.getWalletKey
         */
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

        /**
         * Encrypts given secret seed with given key
         */
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

        /**
         * @return HEX-encoded wallet ID
         */
        fun getWalletIdHex(login: String, password: CharArray,
                           kdfAttributes: KdfAttributes): String {
            val passwordCharBuffer = CharBuffer.wrap(password)
            val passwordByteBuffer = Charset.defaultCharset().encode(passwordCharBuffer)
            passwordCharBuffer.clear()

            val passwordBytes = ByteArray(passwordByteBuffer.remaining())
            passwordByteBuffer.get(passwordBytes).clear()

            val result = String(deriveKey(login.toByteArray(), passwordBytes,
                    WALLET_ID_MASTER_KEY.toByteArray(), kdfAttributes).encodeHex())
            passwordBytes.fill(0)

            return result
        }

        /**
         * Encrypts given account data.
         */
        fun encryptWalletAccount(email: String,
                                 seed: CharArray,
                                 accountId: String,
                                 encryptionKey: ByteArray,
                                 keyDerivationSalt: ByteArray)
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
    }
}