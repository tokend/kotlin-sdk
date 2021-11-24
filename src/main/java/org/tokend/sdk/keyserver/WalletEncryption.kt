package org.tokend.sdk.keyserver

import org.tokend.crypto.cipher.Aes256GCM
import org.tokend.crypto.ecdsa.erase
import org.tokend.sdk.keyserver.models.EncryptedWalletAccount
import org.tokend.sdk.keyserver.models.KeychainData
import org.tokend.sdk.keyserver.seedreader.KeychainDataSeedsArrayReader
import org.tokend.sdk.keyserver.seedreader.KeychainDataSingleSeedReader
import org.tokend.wallet.Account
import java.nio.ByteBuffer
import java.nio.CharBuffer
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
    fun encryptSecretSeed(
        seed: CharArray,
        iv: ByteArray,
        walletEncryptionKey: ByteArray
    ): KeychainData {
        return encryptSecretSeeds(listOf(seed), iv, walletEncryptionKey)
    }

    /**
     * Wraps given secret seeds into JSON object
     * and encrypts it with given key
     *
     * @param seeds secret seeds of the [Account]. The first will be used
     * for legacy format ("seed" attribute)
     * @param iv non-empty cipher initialization vector
     * @param walletEncryptionKey 32 bytes encryption key
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see Account.secretSeed
     * @see Aes256GCM
     */
    @JvmStatic
    fun encryptSecretSeeds(
        seeds: List<CharArray>,
        iv: ByteArray,
        walletEncryptionKey: ByteArray
    ): KeychainData {
        val primarySeed = seeds.first()

        val jsonStart = """{"seed":""""
        val jsonMiddle = """","seeds":["""
        val jsonEnd = """]}"""

        val jsonCharBuffer = CharBuffer.allocate(
            jsonStart.length +
                    primarySeed.size +
                    jsonMiddle.length +
                    seeds.sumBy { it.size } +
                    // "...", - last comma
                    seeds.size * 3 - 1 +
                    jsonEnd.length
        )

        jsonCharBuffer.apply {
            put(jsonStart)
            put(primarySeed)
            put(jsonMiddle)
            seeds.forEachIndexed { i, seed ->
                put('"')
                put(seed)
                put('"')
                if (i != seeds.size - 1) {
                    put(',')
                }
            }
            put(jsonEnd)

            rewind()
        }

        val jsonByteBuffer = Charsets.UTF_8.encode(jsonCharBuffer)
        jsonCharBuffer.clear()
        jsonCharBuffer.array().erase()

        val jsonBytes = ByteArray(jsonByteBuffer.remaining())
        jsonByteBuffer.get(jsonBytes).clear()

        val encrypted = Aes256GCM(iv).encrypt(jsonBytes, walletEncryptionKey)
        jsonBytes.erase()

        return KeychainData.fromRaw(iv, encrypted)
    }

    /**
     * Decrypts single secret seed wrapped into JSON object with given key
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see encryptSecretSeed
     */
    @JvmStatic
    fun decryptSecretSeed(
        keychainData: KeychainData,
        walletEncryptionKey: ByteArray
    ): CharArray {
        return decryptSecretSeeds(keychainData, walletEncryptionKey).first()
    }

    /**
     * Decrypts secret seeds wrapped into JSON object with given key.
     * It supports both legacy ("seed") and new ("seeds") formats.
     * If there are both legacy and new attributes then it returns array values.
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see encryptSecretSeeds
     */
    fun decryptSecretSeeds(
        keychainData: KeychainData,
        walletEncryptionKey: ByteArray
    ): List<CharArray> {
        val iv = keychainData.iv
        val cipherText = keychainData.cipherText

        val seedJsonBytes = Aes256GCM(iv).decrypt(cipherText, walletEncryptionKey)
        val seedJsonByteBuffer = ByteBuffer.wrap(seedJsonBytes)

        val seedJsonCharBuffer = Charsets.UTF_8.decode(seedJsonByteBuffer)
        seedJsonByteBuffer.clear()
        seedJsonBytes.erase()

        val arrayParser = KeychainDataSeedsArrayReader().apply { run(seedJsonCharBuffer) }
        seedJsonCharBuffer.rewind()
        val singleParser = KeychainDataSingleSeedReader().apply { run(seedJsonCharBuffer) }

        seedJsonCharBuffer.clear()
        seedJsonCharBuffer.array().erase()

        return if (arrayParser.readSeeds.isNotEmpty())
            arrayParser.readSeeds
        else
            singleParser.readSeed
                ?.let { listOf(it) }
                ?: throw IllegalStateException("Unable to parse seed")
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
    fun encryptAccount(
        email: String,
        seed: CharArray,
        accountId: String,
        walletEncryptionKey: ByteArray,
        keyDerivationSalt: ByteArray
    ): EncryptedWalletAccount {
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
    fun encryptAccount(
        email: String,
        account: Account,
        walletEncryptionKey: ByteArray,
        keyDerivationSalt: ByteArray
    ): EncryptedWalletAccount {
        return encryptAccounts(email, listOf(account), walletEncryptionKey, keyDerivationSalt)
    }

    /**
     * Encrypts given account assuming that first account is the root one
     *
     * @param email wallet email
     * @param accounts accounts to encrypt in specified order
     * @param walletEncryptionKey 32 bytes encryption key
     * @param keyDerivationSalt KDF salt used for [walletEncryptionKey] derivation
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see Aes256GCM
     */
    @JvmStatic
    fun encryptAccounts(
        email: String,
        accounts: List<Account>,
        walletEncryptionKey: ByteArray,
        keyDerivationSalt: ByteArray
    ): EncryptedWalletAccount {
        val iv = SecureRandom.getSeed(IV_LENGTH)

        val seeds = accounts.map {
            requireNotNull(it.secretSeed) {
                "Account $it has no secret seed"
            }
        }
        val mainAccount = requireNotNull(accounts.firstOrNull()) {
            "There must be at least one account"
        }

        val encryptedSeedsKeychainData = encryptSecretSeeds(
            seeds = seeds,
            iv = iv,
            walletEncryptionKey = walletEncryptionKey
        )

        seeds.forEach(CharArray::erase)

        return EncryptedWalletAccount(
            accountId = mainAccount.accountId,
            keychainData = encryptedSeedsKeychainData,
            salt = keyDerivationSalt,
            email = email
        )
    }

    /**
     * Encrypts given account assuming that first account is the root one
     *
     * @param accounts accounts to encrypt in specified order
     * @param walletEncryptionKey 32 bytes encryption key
     *
     * @see WalletKeyDerivation.deriveWalletEncryptionKey
     * @see Aes256GCM
     */
    @JvmStatic
    fun encryptAccountsV2(
        accounts: List<Account>,
        walletEncryptionKey: ByteArray,
    ): KeychainData {
        val iv = SecureRandom.getSeed(IV_LENGTH)

        val seeds = accounts.map {
            requireNotNull(it.secretSeed) {
                "Account $it has no secret seed"
            }
        }

        val encryptedSeedsKeychainData = encryptSecretSeeds(
            seeds = seeds,
            iv = iv,
            walletEncryptionKey = walletEncryptionKey
        )

        seeds.forEach(CharArray::erase)

        return encryptedSeedsKeychainData
    }

    private const val IV_LENGTH = 12
}