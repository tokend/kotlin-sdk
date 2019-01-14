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
import org.tokend.wallet.*
import org.tokend.wallet.xdr.Operation
import org.tokend.wallet.xdr.op_extensions.RemoveMasterKeyOp
import org.tokend.wallet.xdr.op_extensions.UpdateSignerOp
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

        return if (isRecovery) {
            WalletInfo(accountId, email, hexWalletId, CharArray(0), loginParams)
        } else {
            WalletInfo(accountId, email, hexWalletId,
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

    /**
     * Loads default login params, creates a wallet and submits it to the system.
     *
     * @see createWallet
     * @see saveWallet
     * @see getLoginParams
     */
    @JvmOverloads
    @Throws(EmailAlreadyTakenException::class)
    fun createAndSaveWallet(email: String,
                            password: CharArray,
                            rootAccount: Account = Account.random(),
                            recoveryAccount: Account = Account.random()
    ): WalletCreateResult {
        val loginParams = getLoginParams()

        val kdf = loginParams.kdfAttributes
        val kdfVersion = loginParams.id

        val result = KeyStorage.createWallet(
                email,
                password,
                kdf,
                kdfVersion,
                rootAccount,
                recoveryAccount
        )

        saveWallet(result.walletData)

        return result
    }

    companion object {
        private const val WALLET_ID_MASTER_KEY = "WALLET_ID"
        private const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"
        private const val IV_LENGTH = 12
        const val KDF_SALT_LENGTH_BYTES = 16

        /**
         * @return key for secret seed encryption/decryption
         */
        @JvmStatic
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

        @JvmStatic
        private fun deriveKey(login: ByteArray,
                              password: ByteArray,
                              masterKey: ByteArray,
                              kdfAttributes: KdfAttributes): ByteArray {
            val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n, kdfAttributes.r,
                    kdfAttributes.p, login, masterKey)
            val salt = kdfAttributes.salt
                    ?: throw IllegalArgumentException("KDF salt is required for derivation")
            return derivation.derive(password, salt, kdfAttributes.bytes)
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
        @JvmStatic
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
        @JvmStatic
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
        @JvmStatic
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
        @JvmStatic
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

        /**
         * @return completed wallet for sign up or update
         * @param email user's email
         * @param password user's password
         * @param kdfAttributes system KDF attributes.
         * For password change or recovery use existing.
         * If kdf salt is null it will be generated.
         * @param kdfVersion system KDF version.
         * For password change or recovery use existing
         */
        @JvmStatic
        @JvmOverloads
        fun createWallet(
                email: String,
                password: CharArray,
                kdfAttributes: KdfAttributes,
                kdfVersion: Long,
                rootAccount: Account = Account.random(),
                recoveryAccount: Account = Account.random()
        ): WalletCreateResult {
            val rootAccountSeed = rootAccount.secretSeed
                    ?: throw IllegalArgumentException("Root account must have private key")
            val recoveryAccountSeed = recoveryAccount.secretSeed
                    ?: throw IllegalArgumentException("Recovery account must have private key")
            val email = if (kdfVersion == 2L) email.toLowerCase() else email

            val kdfSalt = kdfAttributes.salt ?: generateKdfSalt()
            kdfAttributes.salt = kdfSalt

            val walletKey = KeyStorage.getWalletKey(email, password, kdfAttributes)
            val walletId = KeyStorage.getWalletIdHex(email, password, kdfAttributes)


            val encryptedSeed = KeyStorage.encryptWalletAccount(
                    email,
                    rootAccountSeed,
                    rootAccount.accountId,
                    walletKey,
                    kdfSalt
            )

            val wallet = WalletData(walletId, encryptedSeed, listOf())

            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_KDF,
                            WalletRelation.RELATION_KDF,
                            kdfVersion.toString()
                    )
            )

            val passwordFactorAccount = Account.random()
            val encryptedPasswordFactor =
                    KeyStorage.encryptWalletAccount(
                            email,
                            passwordFactorAccount.secretSeed!!,
                            passwordFactorAccount.accountId,
                            walletKey,
                            kdfSalt
                    )
            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_PASSWORD_FACTOR,
                            WalletRelation.RELATION_PASSWORD,
                            walletId,
                            passwordFactorAccount.accountId,
                            encryptedPasswordFactor
                    )
            )

            val recoveryWalletKey = KeyStorage.getWalletKey(
                    email, recoveryAccountSeed, kdfAttributes)
            val recoveryWalletId = KeyStorage.getWalletIdHex(email,
                    recoveryAccountSeed, kdfAttributes)

            val encryptedRecovery =
                    KeyStorage.encryptWalletAccount(
                            email,
                            recoveryAccount.secretSeed!!,
                            recoveryAccount.accountId,
                            recoveryWalletKey,
                            kdfSalt
                    )

            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_RECOVERY,
                            WalletRelation.RELATION_RECOVERY,
                            recoveryWalletId,
                            recoveryAccount.accountId,
                            encryptedRecovery)
            )

            return WalletCreateResult(
                    wallet,
                    rootAccount,
                    recoveryAccount
            )
        }

        /**
         * @return completed wallet for sign up or update
         * @param email user's email
         * @param password user's password
         * @param loginParams system KDF params.
         * For password change or recovery use existing.
         * If kdf salt is null it will be generated.
         */
        @JvmStatic
        @JvmOverloads
        fun createWallet(
                email: String,
                password: CharArray,
                loginParams: LoginParams,
                rootAccount: Account = Account.random(),
                recoveryAccount: Account = Account.random()
        ): WalletCreateResult {
            val kdf = loginParams.kdfAttributes
            val kdfVersion = loginParams.id

            return createWallet(email, password, kdf, kdfVersion, rootAccount, recoveryAccount)
        }

        /**
         * @return transaction for password change or recovery
         * @param networkParams params of current TokenD network
         * @param originalAccountId original account ID of the wallet
         * @param signers list of current account signers
         * @param currentAccount active or recovery keypair
         * @param newAccount keypair to set as active
         */
        fun createSignersUpdateTransaction(networkParams: NetworkParams,
                                           originalAccountId: String,
                                           currentAccount: Account,
                                           signers: Collection<org.tokend.sdk.api.accounts.model.Account.Signer>,
                                           newAccount: Account): Transaction {
            val operationBodies = mutableListOf<Operation.OperationBody>()

            // Add new signer.
            val currentSigner =
                    signers.find {
                        it.accountId == currentAccount.accountId
                    } ?: org.tokend.sdk.api.accounts.model.Account.Signer(originalAccountId)

            operationBodies.add(
                    Operation.OperationBody.SetOptions(
                            UpdateSignerOp(
                                    newAccount.accountId,
                                    currentSigner.weight,
                                    currentSigner.type,
                                    currentSigner.identity
                            )
                    )
            )

            // Remove other signers.
            signers
                    .sortedBy {
                        // Remove current signer lastly, otherwise tx will be failed.
                        it.accountId == currentAccount.accountId
                    }
                    .forEach {
                        if (it.accountId != newAccount.accountId) {
                            // Master key removal is specific.
                            if (it.accountId == originalAccountId) {
                                operationBodies.add(
                                        Operation.OperationBody.SetOptions(
                                                RemoveMasterKeyOp()
                                        )
                                )
                            } else {
                                // Other keys can be removed by setting 0 weight.
                                operationBodies.add(
                                        Operation.OperationBody.SetOptions(
                                                UpdateSignerOp(it.accountId,
                                                        0, 1, it.identity)
                                        )
                                )
                            }
                        }
                    }

            val transaction =
                    TransactionBuilder(networkParams,
                            PublicKeyFactory.fromAccountId(originalAccountId))
                            .apply {
                                operationBodies.forEach { operationBody ->
                                    addOperation(operationBody)
                                }
                            }
                            .build()

            transaction.addSignature(currentAccount)

            return transaction
        }

        /**
         * Generate salt for system KDF params.
         */
        @JvmStatic
        fun generateKdfSalt(): ByteArray {
            return SecureRandom().generateSeed(KDF_SALT_LENGTH_BYTES)
        }
    }
}