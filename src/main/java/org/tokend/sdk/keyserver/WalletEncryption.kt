package org.tokend.sdk.keyserver

import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.sdk.keyserver.models.EncryptedWalletAccount
import org.tokend.sdk.keyserver.models.KeychainData
import org.tokend.wallet.Account
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.security.SecureRandom

object WalletEncryption {
    /**
     * Wraps given secret seed into JSON object
     * and encrypts it with given key
     *
     * @param seed secret seed of the [Account]
     * @param iv non-empty cipher initialization vector
     * @param walletEncryptionKey 32 bytes encryption key
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see Account.secretSeed
     * @see Aes256GCM
     */
    @JvmStatic
    fun encryptSecretSeed(seed: CharArray,
                          iv: ByteArray,
                          walletEncryptionKey: ByteArray): KeychainData {
        val jsonStartChars = "{\"seed\":\"".toCharArray()
        val jsonEndChars = "\"}".toCharArray()
        val jsonChars = jsonStartChars.plus(seed).plus(jsonEndChars)

        val jsonCharBuffer = CharBuffer.wrap(jsonChars)
        val jsonByteBuffer = Charset.defaultCharset().encode(jsonCharBuffer)
        jsonCharBuffer.clear()
        jsonChars.fill('0')

        val jsonBytes = ByteArray(jsonByteBuffer.remaining())
        jsonByteBuffer.get(jsonBytes).clear()

        val encrypted = Aes256GCM(iv).encrypt(jsonBytes, walletEncryptionKey)
        jsonBytes.fill(0)

        return KeychainData.fromRaw(iv, encrypted)
    }

    /**
     * Decrypts secret seed wrapped into JSON object with given key
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see encryptSecretSeed
     */
    @JvmStatic
    fun decryptSecretSeed(keychainData: KeychainData,
                          walletEncryptionKey: ByteArray): CharArray {
        val iv = keychainData.iv
        val cipherText = keychainData.cipherText

        val seedJsonBytes = Aes256GCM(iv).decrypt(cipherText, walletEncryptionKey)
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
     * Encrypts given account data
     *
     * @param email wallet email
     * @param seed secret seed of the [Account]
     * @param accountId ID i.e. a public key of the [Account]
     * @param walletEncryptionKey 32 bytes encryption key
     * @param keyDerivationSalt KDF salt used for [walletEncryptionKey] derivation
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see Account.secretSeed
     * @see Aes256GCM
     */
    @JvmStatic
    fun encryptAccount(email: String,
                       seed: CharArray,
                       accountId: String,
                       walletEncryptionKey: ByteArray,
                       keyDerivationSalt: ByteArray): EncryptedWalletAccount {
        val iv = SecureRandom.getSeed(IV_LENGTH)

        val encryptedSeedKeychainData = encryptSecretSeed(seed, iv, walletEncryptionKey)

        return EncryptedWalletAccount(
                accountId,
                email,
                keyDerivationSalt,
                encryptedSeedKeychainData
        )
    }

    /**
     * Encrypts given account
     *
     * @param email wallet email
     * @param account [Account] to encrypt
     * @param walletEncryptionKey 32 bytes encryption key
     * @param keyDerivationSalt KDF salt used for [walletEncryptionKey] derivation
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see Aes256GCM
     */
    @JvmStatic
    fun encryptAccount(email: String,
                       account: Account,
                       walletEncryptionKey: ByteArray,
                       keyDerivationSalt: ByteArray): EncryptedWalletAccount {
        val seed = account.secretSeed
                ?: throw IllegalArgumentException("Provided account has no secret seed")

        return this.encryptAccount(email, seed, account.accountId,
                walletEncryptionKey, keyDerivationSalt)
    }

    private const val IV_LENGTH = 12
}