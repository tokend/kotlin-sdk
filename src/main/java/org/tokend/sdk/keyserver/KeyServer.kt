package org.tokend.sdk.keyserver

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedCallableApiRequest
import org.tokend.sdk.api.v3.keyvalue.KeyValueStorageApiV3
import org.tokend.sdk.api.v3.signers.SignersApiV3
import org.tokend.sdk.api.wallets.WalletsApi
import org.tokend.sdk.api.wallets.model.EmailAlreadyTakenException
import org.tokend.sdk.api.wallets.model.EmailNotVerifiedException
import org.tokend.sdk.api.wallets.model.InvalidCredentialsException
import org.tokend.sdk.keyserver.models.*
import org.tokend.sdk.utils.extentions.isNotFound
import org.tokend.wallet.*
import org.tokend.wallet.Transaction
import org.tokend.wallet.xdr.*
import retrofit2.HttpException

class KeyServer constructor(
        private val walletsApi: WalletsApi
) {
    // region Obtain
    /**
     * Loads user's wallet and decrypts secret seed.
     * @see buildWalletInfo
     */
    @JvmOverloads
    fun getWalletInfo(login: String,
                      password: CharArray,
                      isRecovery: Boolean = false): ApiRequest<WalletInfo> {
        return MappedCallableApiRequest(
                { buildWalletInfo(login, password, isRecovery) },
                { it }
        )
    }

    /**
     * @see getLoginParams
     * @see getWalletData
     */
    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class
    )
    private fun buildWalletInfo(login: String,
                                password: CharArray,
                                isRecovery: Boolean = false): WalletInfo {
        val loginParams = getLoginParams(login, isRecovery).execute().get()

        val derivationLogin = login.toLowerCase()

        val hexWalletId = WalletKeyDerivation
                .deriveAndEncodeWalletId(derivationLogin, password, loginParams.kdfAttributes)
        val walletKey = WalletKeyDerivation
                .deriveWalletEncryptionKey(derivationLogin, password, loginParams.kdfAttributes)

        val walletData = getWalletData(hexWalletId).execute().get()

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
    fun getLoginParams(login: String? = null, isRecovery: Boolean = false): ApiRequest<LoginParams> {
        return walletsApi.getLoginParams(login, isRecovery)
    }

    /**
     * Loads wallet by wallet ID.
     */
    @Throws(InvalidCredentialsException::class,
            EmailNotVerifiedException::class,
            HttpException::class)
    fun getWalletData(walletId: String): ApiRequest<WalletData> {
        return walletsApi.getById(walletId)
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
    fun saveWallet(walletData: WalletData): ApiRequest<WalletData> {
        return walletsApi.create(walletData)
    }

    /**
     * Updates wallet by given wallet ID with given data.
     */
    fun updateWallet(walletId: String, walletData: WalletData): ApiRequest<Void> {
        return walletsApi.update(walletId, walletData)
    }
    // endregion

    /**
     * Loads default login params, creates a wallet and submits it to the system.
     * @see getCreateAndSaveWalletResult
     */
    @JvmOverloads
    fun createAndSaveWallet(email: String,
                            password: CharArray,
                            rootAccount: Account = Account.random()
    ): ApiRequest<WalletCreateResult> {
        return MappedCallableApiRequest(
                { getCreateAndSaveWalletResult(email, password, rootAccount) },
                { it }
        )
    }

    /**
     * @see createWallet
     * @see saveWallet
     * @see getLoginParams
     */
    @Throws(EmailAlreadyTakenException::class)
    private fun getCreateAndSaveWalletResult(email: String,
                                             password: CharArray,
                                             rootAccount: Account = Account.random()
    ): WalletCreateResult {
        val loginParams = getLoginParams().execute().get()

        val kdf = loginParams.kdfAttributes
        val kdfVersion = loginParams.id

        val result = createWallet(
                email,
                password,
                kdf,
                kdfVersion,
                rootAccount
        )

        val remoteWalletData = saveWallet(result.walletData)
                .execute()
                .get()

        return result.copy(walletData = remoteWalletData)
    }

    @JvmOverloads
    fun updateWalletPassword(currentWalletInfo: WalletInfo,
                             currentAccount: Account,
                             newPassword: CharArray,
                             newAccount: Account,
                             networkParams: NetworkParams,
                             signersApi: SignersApiV3,
                             keyValueApi: KeyValueStorageApiV3,
                             defaultSignerDetailsJson: String = "{}"): ApiRequest<WalletInfo> {
        return MappedCallableApiRequest(
                {
                    getUpdateWalletPasswordResult(currentWalletInfo, currentAccount,
                            newPassword, newAccount, networkParams, signersApi, keyValueApi,
                            defaultSignerDetailsJson)
                },
                { it }
        )
    }

    @JvmOverloads
    fun updateWalletPassword(currentWalletInfo: WalletInfo,
                             currentAccount: Account,
                             newPassword: CharArray,
                             newAccount: Account,
                             networkParams: NetworkParams,
                             currentSigners: List<SignerData>,
                             defaultSignerRole: Uint64,
                             defaultSignerDetailsJson: String = "{}"): ApiRequest<WalletInfo> {
        return MappedCallableApiRequest(
                {
                    getUpdateWalletPasswordResult(currentWalletInfo, currentAccount,
                            newPassword, newAccount, networkParams, currentSigners, defaultSignerRole,
                            defaultSignerDetailsJson)
                },
                { it }
        )
    }

    private fun getUpdateWalletPasswordResult(currentWalletInfo: WalletInfo,
                                              currentAccount: Account,
                                              newPassword: CharArray,
                                              newAccount: Account,
                                              networkParams: NetworkParams,
                                              signersApi: SignersApiV3,
                                              keyValueApi: KeyValueStorageApiV3,
                                              defaultSignerDetailsJson: String): WalletInfo {
        val signers: List<SignerData> = try {
            signersApi
                    .get(currentWalletInfo.accountId)
                    .execute()
                    .get()
                    .map { SignerData(it) }
        } catch (e: HttpException) {
            if (e.isNotFound())
                emptyList()
            else
                throw e
        }

        val defaultSignerRole = try {
            keyValueApi
                    .getById(DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY)
                    .execute()
                    .get()
                    .value
                    .u32!!
                    .u32!!
        } catch (e: Exception) {
            throw IllegalStateException("Unable to obtain default role for signer " +
                    "by key $DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY", e)
        }

        return getUpdateWalletPasswordResult(currentWalletInfo, currentAccount,
                newPassword, newAccount, networkParams,
                signers, defaultSignerRole, defaultSignerDetailsJson
        )
    }

    private fun getUpdateWalletPasswordResult(currentWalletInfo: WalletInfo,
                                              currentAccount: Account,
                                              newPassword: CharArray,
                                              newAccount: Account,
                                              networkParams: NetworkParams,
                                              currentSigners: List<SignerData>,
                                              defaultSignerRole: Uint64,
                                              defaultSignerDetailsJson: String): WalletInfo {
        val signersUpdateTx = createSignersUpdateTransaction(
                networkParams = networkParams,
                currentAccount = currentAccount,
                newAccount = newAccount,
                originalAccountId = currentWalletInfo.accountId,
                signers = currentSigners,
                defaultSignerRole = defaultSignerRole,
                defaultSignerDetailsJson = defaultSignerDetailsJson
        )

        val newLoginParams = currentWalletInfo.loginParams.copy(
                kdfAttributes = currentWalletInfo.loginParams.kdfAttributes.copy()
        )

        // New KDF salt should be generated.
        newLoginParams.kdfAttributes.salt = null

        val newWallet = createWallet(
                email = currentWalletInfo.email,
                password = newPassword,
                loginParams = newLoginParams,
                rootAccount = newAccount
        )

        newLoginParams.kdfAttributes.encodedSalt = newWallet.walletData.attributes?.salt

        newWallet.walletData.addTransactionRelation(signersUpdateTx)

        updateWallet(
                currentWalletInfo.walletIdHex,
                newWallet.walletData
        )
                .execute()

        return currentWalletInfo.copy(
                walletIdHex = newWallet.walletData.id!!,
                loginParams = newLoginParams,
                secretSeed = newAccount.secretSeed!!
        )
    }

    /**
     * Updates wallet with new password according to the KYC recovery flow.
     * Temp signer will be added in order to request KYC recovery after
     * sign in with new credentials.
     *
     * This requires email TFA confirmation.
     */
    fun recoverWalletPassword(email: String,
                              newPassword: CharArray,
                              newAccount: Account): ApiRequest<WalletCreateResult> {
        return MappedCallableApiRequest(
                { getRecoverWalletPasswordResult(email, newPassword, newAccount) },
                { it }
        )
    }

    private fun getRecoverWalletPasswordResult(email: String,
                                               newPassword: CharArray,
                                               newAccount: Account): WalletCreateResult {
        val currentLoginParams = getLoginParams(email, true)
                .execute()
                .get()

        val newLoginParams = currentLoginParams.copy(
                kdfAttributes = currentLoginParams.kdfAttributes.copy()
        )

        // New KDF salt should be generated.
        newLoginParams.kdfAttributes.salt = null

        val recoveryWallet = createWallet(
                email = email,
                password = newPassword,
                loginParams = newLoginParams,
                rootAccount = newAccount,
                walletType = WalletData.TYPE_RECOVERY
        )

        newLoginParams.kdfAttributes.encodedSalt = recoveryWallet.walletData.attributes?.salt

        val newWalletId = recoveryWallet.walletData.id
                ?: throw  IllegalStateException("Missing wallet ID in new wallet data")

        updateWallet(
                newWalletId,
                recoveryWallet.walletData
        )
                .execute()

        return recoveryWallet
    }

    companion object {
        private const val RECOVERY_SIGNER_ROLE_ID = 1L
        private const val DEFAULT_SIGNER_IDENTITY = 0
        private const val DEFAULT_SIGNER_WEIGHT = 1000
        private const val DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY = "signer_role:default"

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
                walletType: String = WalletData.TYPE_DEFAULT
        ): WalletCreateResult {
            val derivationEmail = email.toLowerCase()

            val kdfSalt = kdfAttributes.salt ?: WalletKeyDerivation.generateKdfSalt()
            val kdfAttributesWithSalt = kdfAttributes.copy()
            kdfAttributesWithSalt.salt = kdfSalt

            val walletKey = WalletKeyDerivation
                    .deriveWalletEncryptionKey(derivationEmail, password, kdfAttributesWithSalt)
            val walletId = WalletKeyDerivation
                    .deriveAndEncodeWalletId(derivationEmail, password, kdfAttributesWithSalt)

            val encryptedSeed = WalletEncryption.encryptAccount(
                    derivationEmail,
                    rootAccount,
                    walletKey,
                    kdfSalt
            )

            val wallet = WalletData(walletId, encryptedSeed, listOf(), walletType)

            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_KDF,
                            WalletRelation.RELATION_KDF,
                            kdfVersion.toString()
                    )
            )

            val passwordFactorAccount = Account.random()
            val encryptedPasswordFactor = WalletEncryption.encryptAccount(
                    derivationEmail,
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

            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_SIGNER,
                            WalletRelation.RELATION_SIGNER,
                            encryptedSeed.accountId,
                            encryptedSeed.accountId
                    )
            )

            return WalletCreateResult(
                    wallet,
                    rootAccount
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
                walletType: String = WalletData.TYPE_DEFAULT
        ): WalletCreateResult {
            val kdf = loginParams.kdfAttributes
            val kdfVersion = loginParams.id

            return createWallet(email, password, kdf, kdfVersion, rootAccount, walletType)
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
                                           signers: Collection<SignerData>,
                                           newAccount: Account,
                                           defaultSignerRole: Uint64,
                                           defaultSignerDetailsJson: String): Transaction {
            val operationBodies = mutableListOf<Operation.OperationBody>()

            // Add new signer.
            val newSignerData = signers
                    .find {
                        it.id == currentAccount.accountId
                                && it.roleId != RECOVERY_SIGNER_ROLE_ID
                    }
                    ?: SignerData(
                            identity = DEFAULT_SIGNER_IDENTITY,
                            weight = DEFAULT_SIGNER_WEIGHT,
                            roleId = defaultSignerRole,
                            detailsJson = defaultSignerDetailsJson,
                            id = newAccount.accountId
                    )

            operationBodies.add(
                    Operation.OperationBody.ManageSigner(
                            ManageSignerOp(
                                    ManageSignerOp.ManageSignerOpData.Create(
                                            UpdateSignerData(
                                                    publicKey = PublicKeyFactory.fromAccountId(
                                                            newAccount.accountId
                                                    ),
                                                    identity = newSignerData.identity,
                                                    weight = newSignerData.weight,
                                                    roleID = newSignerData.roleId,
                                                    details = newSignerData.detailsJson ?: "{}",
                                                    ext = EmptyExt.EmptyVersion()
                                            )
                                    ),
                                    EmptyExt.EmptyVersion()
                            )
                    )
            )

            // Remove other signers.
            signers
                    .sortedBy {
                        // Remove current signer lastly, otherwise tx will be failed.
                        it.id == currentAccount.accountId
                    }
                    .filter {
                        // Do not remove recovery signer.
                        it.roleId != RECOVERY_SIGNER_ROLE_ID
                    }
                    .forEach {
                        operationBodies.add(
                                Operation.OperationBody.ManageSigner(
                                        ManageSignerOp(
                                                ManageSignerOp.ManageSignerOpData.Remove(
                                                        RemoveSignerData(
                                                                publicKey = PublicKeyFactory.fromAccountId(
                                                                        it.id
                                                                ),
                                                                ext = EmptyExt.EmptyVersion()
                                                        )
                                                ),
                                                EmptyExt.EmptyVersion()
                                        )
                                )
                        )
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