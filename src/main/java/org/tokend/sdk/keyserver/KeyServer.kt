package org.tokend.sdk.keyserver

import org.tokend.sdk.api.wallets.WalletsApi
import org.tokend.sdk.api.wallets.model.EmailAlreadyTakenException
import org.tokend.sdk.api.wallets.model.EmailNotVerifiedException
import org.tokend.sdk.api.wallets.model.InvalidCredentialsException
import org.tokend.sdk.keyserver.models.*
import org.tokend.wallet.*
import org.tokend.wallet.xdr.Operation
import org.tokend.wallet.xdr.op_extensions.RemoveMasterKeyOp
import org.tokend.wallet.xdr.op_extensions.UpdateSignerOp
import retrofit2.HttpException

class KeyServer constructor(
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
    @JvmOverloads
    fun getWalletInfo(login: String,
                      password: CharArray,
                      isRecovery: Boolean = false): WalletInfo {
        val loginParams = getLoginParams(login, isRecovery)

        val tempLogin = if (loginParams.id == 2L) login.toLowerCase() else login

        val hexWalletId = WalletKeyDerivation
                .deriveAndEncodeWalletId(tempLogin, password, loginParams.kdfAttributes)
        val walletKey = WalletKeyDerivation
                .deriveWalletEncryptionKey(tempLogin, password, loginParams.kdfAttributes)

        val walletData = getWalletData(hexWalletId)

        val keychainData = walletData.attributes?.keychainData
                ?: throw IllegalStateException("Wallet data has no keychain data")
        val accountId = walletData.attributes?.accountId
                ?: throw IllegalStateException("Wallet data has no account ID")
        val email = walletData.attributes?.email
                ?: throw IllegalStateException("Wallet data has no email")

        return if (isRecovery) {
            WalletInfo(
                    accountId,
                    email,
                    hexWalletId,
                    password,
                    loginParams
            )
        } else {
            WalletInfo(
                    accountId,
                    email,
                    hexWalletId,
                    WalletEncryption.decryptSecretSeed(keychainData, walletKey),
                    loginParams
            )
        }
    }

    /**
     * Loads KDF params.
     * If no [login] specified will return default [LoginParams] without [KdfAttributes.salt]
     */
    @Throws(InvalidCredentialsException::class, HttpException::class)
    @JvmOverloads
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

        val result = KeyServer.createWallet(
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
            val recoveryAccountSeed = recoveryAccount.secretSeed
                    ?: throw IllegalArgumentException("Recovery account must have private key")
            val email = if (kdfVersion == 2L) email.toLowerCase() else email

            val kdfSalt = kdfAttributes.salt ?: WalletKeyDerivation.generateKdfSalt()
            kdfAttributes.salt = kdfSalt

            val walletKey = WalletKeyDerivation
                    .deriveWalletEncryptionKey(email, password, kdfAttributes)
            val walletId = WalletKeyDerivation
                    .deriveAndEncodeWalletId(email, password, kdfAttributes)

            val encryptedSeed = WalletEncryption.encryptAccount(
                    email,
                    rootAccount,
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
            val encryptedPasswordFactor = WalletEncryption.encryptAccount(
                    email,
                    passwordFactorAccount,
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

            val recoveryWalletKey = WalletKeyDerivation
                    .deriveWalletEncryptionKey(email, recoveryAccountSeed, kdfAttributes)
            val recoveryWalletId = WalletKeyDerivation
                    .deriveAndEncodeWalletId(email, recoveryAccountSeed, kdfAttributes)

            val encryptedRecovery = WalletEncryption.encryptAccount(
                    email,
                    recoveryAccount,
                    recoveryWalletKey,
                    kdfSalt
            )

            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_RECOVERY,
                            WalletRelation.RELATION_RECOVERY,
                            recoveryWalletId,
                            recoveryAccount.accountId,
                            encryptedRecovery
                    )
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
    }
}