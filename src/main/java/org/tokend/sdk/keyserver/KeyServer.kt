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
    fun getWalletInfo(
        login: String,
        password: CharArray,
        isRecovery: Boolean = false,
        queryMap: Map<String, Any>? = null
    ): ApiRequest<WalletInfo> {
        return MappedCallableApiRequest(
            { buildWalletInfo(login, password, isRecovery, queryMap) },
            { it }
        )
    }

    /**
     * @see getLoginParams
     * @see getExistingWallet
     */
    @Throws(
        InvalidCredentialsException::class,
        EmailNotVerifiedException::class,
        HttpException::class
    )
    private fun buildWalletInfo(
        login: String,
        password: CharArray,
        isRecovery: Boolean = false,
        queryMap: Map<String, Any>? = null
    ): WalletInfo {
        val loginParams = getLoginParams(login, isRecovery).execute().get()

        val derivationLogin = login.toLowerCase()

        val hexWalletId = WalletKeyDerivation
            .deriveAndEncodeWalletId(derivationLogin, password, loginParams.kdfAttributes)
        val walletKey = WalletKeyDerivation
            .deriveWalletEncryptionKey(derivationLogin, password, loginParams.kdfAttributes)

        val existingWallet = getExistingWallet(hexWalletId, queryMap).execute().get()

        val encodedKeychainData = existingWallet.data.attributes.keychainData
        val accountId = existingWallet.data.attributes.accountId
        val email = existingWallet.data.attributes.email

        return if (isRecovery) {
            WalletInfo(
                accountId,
                email,
                hexWalletId,
                emptyList(),
                loginParams
            )
        } else {
            WalletInfo(
                accountId,
                email,
                hexWalletId,
                WalletEncryption.decryptSecretSeeds(
                    KeychainData.fromEncoded(encodedKeychainData),
                    walletKey
                ),
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
    fun getLoginParams(
        login: String? = null,
        isRecovery: Boolean = false
    ): ApiRequest<LoginParams> {
        return walletsApi.getLoginParams(login, isRecovery)
    }

    /**
     * Loads wallet by wallet ID.
     */
    @Throws(
        InvalidCredentialsException::class,
        EmailNotVerifiedException::class,
        HttpException::class
    )
    @JvmOverloads
    fun getExistingWallet(
        walletId: String,
        queryMap: Map<String, Any>? = null
    ): ApiRequest<ExistingWallet> {
        return walletsApi.getById(walletId, queryMap ?: mapOf())
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
    fun saveWallet(walletCreationData: WalletCreationData): ApiRequest<ExistingWallet> {
        return walletsApi.create(walletCreationData)
    }

    /**
     * Updates wallet by given wallet ID with given data.
     */
    fun updateWallet(walletId: String, walletCreationData: WalletCreationData): ApiRequest<Void> {
        return walletsApi.update(walletId, walletCreationData)
    }
    // endregion

    /**
     * Loads default login params, loads default signer role,
     * creates a wallet with single signer and submits it to the system.
     */
    @JvmOverloads
    fun createAndSaveWallet(
        email: String,
        password: CharArray,
        keyValueApi: KeyValueStorageApiV3,
        rootAccount: Account = Account.random(),
        verificationCode: String? = null,
    ): ApiRequest<WalletCreationResult> {
        return MappedCallableApiRequest(
            {
                val defaultSignerRole = getDefaultSignerRole(keyValueApi)
                getCreateAndSaveWalletResult(
                    email = email,
                    password = password,
                    accounts = listOf(rootAccount),
                    signers = listOf(SignerData(rootAccount.accountId, defaultSignerRole)),
                    verificationCode = verificationCode,
                )
            },
            { it }
        )
    }

    /**
     * Loads default login params, creates a wallet with single signer
     * and submits it to the system.
     */
    @JvmOverloads
    fun createAndSaveWallet(
        email: String,
        password: CharArray,
        defaultSignerRole: Uint64,
        rootAccount: Account = Account.random(),
        verificationCode: String? = null,
    ): ApiRequest<WalletCreationResult> {
        return createAndSaveWallet(
            email = email,
            password = password,
            rootAccount = rootAccount,
            signers = listOf(SignerData(rootAccount.accountId, defaultSignerRole)),
            verificationCode = verificationCode
        )
    }

    /**
     * Loads default login params, creates a wallet with specified signers
     * and submits it to the system.
     */
    fun createAndSaveWallet(
        email: String,
        password: CharArray,
        rootAccount: Account,
        signers: Collection<SignerData>,
        verificationCode: String? = null,
    ): ApiRequest<WalletCreationResult> {
        return MappedCallableApiRequest(
            {
                getCreateAndSaveWalletResult(
                    email = email,
                    password = password,
                    accounts = listOf(rootAccount),
                    signers = signers,
                    verificationCode = verificationCode,
                )
            },
            { it }
        )
    }

    /**
     * Loads default login params, creates a wallet with given signers
     * and given accounts in exact order as they are provided
     * and submits it to the system. The first account is taken as a root one.
     */
    fun createAndSaveWallet(
        email: String,
        password: CharArray,
        accounts: List<Account>,
        signers: Collection<SignerData>,
        verificationCode: String? = null,
    ): ApiRequest<WalletCreationResult> {
        return MappedCallableApiRequest(
            {
                getCreateAndSaveWalletResult(
                    email = email,
                    password = password,
                    accounts = accounts,
                    signers = signers,
                    verificationCode = verificationCode
                )
            },
            { it }
        )
    }

    /**
     * @see createWallet
     * @see saveWallet
     * @see getLoginParams
     */
    @Throws(EmailAlreadyTakenException::class)
    private fun getCreateAndSaveWalletResult(
        email: String,
        password: CharArray,
        accounts: List<Account>,
        signers: Collection<SignerData>,
        verificationCode: String? = null,
    ): WalletCreationResult {
        val loginParams = getLoginParams().execute().get()

        val kdf = loginParams.kdfAttributes
        val kdfVersion = loginParams.id

        val result = createWallet(
            email = email,
            password = password,
            kdfAttributes = kdf,
            kdfVersion = kdfVersion,
            accounts = accounts,
            signers = signers,
            verificationCode = verificationCode
        )

        val remoteWalletData = saveWallet(result.creationData)
            .execute()
            .get()

        return result.copy(isVerified = remoteWalletData.data.attributes.isVerified)
    }

    fun updateWalletPassword(
        currentWalletInfo: WalletInfo,
        currentAccount: Account,
        newPassword: CharArray,
        newAccount: Account,
        networkParams: NetworkParams,
        signersApi: SignersApiV3,
        keyValueApi: KeyValueStorageApiV3
    ): ApiRequest<WalletInfo> {
        return MappedCallableApiRequest(
            {
                getUpdateWalletPasswordResult(
                    currentWalletInfo, currentAccount,
                    newPassword, newAccount, networkParams, signersApi, keyValueApi
                )
            },
            { it }
        )
    }

    fun updateWalletPassword(
        currentWalletInfo: WalletInfo,
        currentAccount: Account,
        newPassword: CharArray,
        newAccount: Account,
        networkParams: NetworkParams,
        currentSigners: List<SignerData>,
        defaultSignerRole: Uint64
    ): ApiRequest<WalletInfo> {
        val currentAccountSigner = currentSigners.find { it.id == currentAccount.accountId }
            ?: SignerData(currentAccount.accountId, defaultSignerRole)

        return MappedCallableApiRequest(
            {
                getUpdateWalletPasswordResult(
                    currentWalletInfo = currentWalletInfo,
                    currentAccount = currentAccount,
                    currentSigners = currentSigners,
                    newAccounts = listOf(newAccount),
                    newPassword = newPassword,
                    networkParams = networkParams,
                    newSigners = listOf(
                        SignerData(
                            id = newAccount.accountId,
                            weight = currentAccountSigner.weight,
                            identity = currentAccountSigner.identity,
                            roleId = currentAccountSigner.roleId,
                            detailsJson = currentAccountSigner.detailsJson
                        )
                    )
                )
            },
            { it }
        )
    }

    fun updateWalletPassword(
        currentWalletInfo: WalletInfo,
        currentAccount: Account,
        newPassword: CharArray,
        newAccounts: List<Account>,
        networkParams: NetworkParams,
        currentSigners: List<SignerData>,
        newSigners: Collection<SignerData>
    ): ApiRequest<WalletInfo> {
        return MappedCallableApiRequest(
            {
                getUpdateWalletPasswordResult(
                    currentWalletInfo = currentWalletInfo,
                    currentAccount = currentAccount,
                    currentSigners = currentSigners,
                    newAccounts = newAccounts,
                    newPassword = newPassword,
                    networkParams = networkParams,
                    newSigners = newSigners
                )
            },
            { it }
        )
    }

    private fun getUpdateWalletPasswordResult(
        currentWalletInfo: WalletInfo,
        currentAccount: Account,
        newPassword: CharArray,
        newAccount: Account,
        networkParams: NetworkParams,
        signersApi: SignersApiV3,
        keyValueApi: KeyValueStorageApiV3
    ): WalletInfo {
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

        val defaultSignerRole = getDefaultSignerRole(keyValueApi)
        val currentAccountSigner = signers.find { it.id == currentAccount.accountId }
            ?: SignerData(currentAccount.accountId, defaultSignerRole)

        return getUpdateWalletPasswordResult(
            currentWalletInfo = currentWalletInfo,
            currentAccount = currentAccount,
            currentSigners = signers,
            newAccounts = listOf(newAccount),
            newPassword = newPassword,
            networkParams = networkParams,
            newSigners = listOf(
                SignerData(
                    id = newAccount.accountId,
                    weight = currentAccountSigner.weight,
                    identity = currentAccountSigner.identity,
                    roleId = currentAccountSigner.roleId,
                    detailsJson = currentAccountSigner.detailsJson
                )
            )
        )
    }

    private fun getUpdateWalletPasswordResult(
        currentWalletInfo: WalletInfo,
        currentAccount: Account,
        newPassword: CharArray,
        newAccounts: List<Account>,
        networkParams: NetworkParams,
        currentSigners: List<SignerData>,
        newSigners: Collection<SignerData>
    ): WalletInfo {
        val signersUpdateTx = createSignersUpdateTransaction(
            networkParams = networkParams,
            currentAccount = currentAccount,
            originalAccountId = currentWalletInfo.accountId,
            signersToAdd = newSigners,
            signersToRemove = currentSigners
                .filterNot {
                    // Do not remove recovery signer.
                    it.roleId == RECOVERY_SIGNER_ROLE_ID
                }
                .map(SignerData::id)
        )

        val newLoginParams = currentWalletInfo.loginParams.copy(
            kdfAttributes = currentWalletInfo.loginParams.kdfAttributes.copy()
        )

        // New KDF salt should be generated.
        newLoginParams.kdfAttributes.salt = null

        val newWallet = createWallet(
            email = currentWalletInfo.email,
            password = newPassword,
            kdfAttributes = newLoginParams.kdfAttributes,
            kdfVersion = newLoginParams.id,
            accounts = newAccounts,
            signers = emptyList()
        )

        newLoginParams.kdfAttributes.encodedSalt = newWallet.creationData.encodedSalt

        newWallet.creationData.relationships["transaction"] =
            WalletRelationship.transaction(signersUpdateTx)

        updateWallet(
            currentWalletInfo.walletId,
            newWallet.creationData
        )
            .execute()

        return WalletInfo(
            accountId = currentWalletInfo.accountId,
            email = currentWalletInfo.email,
            loginParams = newLoginParams,
            secretSeeds = newAccounts.mapNotNull(Account::secretSeed),
            walletId = newWallet.creationData.walletId
        )
    }

    /**
     * Updates wallet with new password according to the KYC recovery flow.
     * Temp signer will be added in order to request KYC recovery after
     * sign in with new credentials.
     *
     * This requires email TFA confirmation.
     */
    fun recoverWalletPassword(
        email: String,
        newPassword: CharArray,
        newAccount: Account
    ): ApiRequest<WalletCreationResult> {
        return recoverWalletPassword(email, newPassword, listOf(newAccount))
    }

    /**
     * Updates wallet with new password according to the KYC recovery flow.
     * Temp signer will be added in order to request KYC recovery after
     * sign in with new credentials.
     *
     * This requires email TFA confirmation.
     */
    fun recoverWalletPassword(
        email: String,
        newPassword: CharArray,
        newAccounts: List<Account>
    ): ApiRequest<WalletCreationResult> {
        return MappedCallableApiRequest(
            { getRecoverWalletPasswordResult(email, newPassword, newAccounts) },
            { it }
        )
    }

    private fun getRecoverWalletPasswordResult(
        email: String,
        newPassword: CharArray,
        newAccounts: List<Account>
    ): WalletCreationResult {
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
            kdfAttributes = newLoginParams.kdfAttributes,
            kdfVersion = newLoginParams.id,
            accounts = newAccounts,
            walletType = WalletCreationData.TYPE_RECOVERY,
            signers = emptyList()
        )

        recoveryWallet.creationData.relationships["signer"] =
            WalletRelationship.signer(
                SignerData(
                    recoveryWallet.creationData.accountId,
                    0
                )
            )

        newLoginParams.kdfAttributes.encodedSalt = recoveryWallet.creationData.encodedSalt

        val newWalletId = recoveryWallet.creationData.walletId

        updateWallet(
            newWalletId,
            recoveryWallet.creationData
        )
            .execute()

        return recoveryWallet
    }

    companion object {
        const val RECOVERY_SIGNER_ROLE_ID = 1L
        const val DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY = "signer_role:default"

        /**
         * @return completed wallet for sign up or update with specified signers
         * @param email user's email
         * @param password user's password
         * @param kdfAttributes system KDF attributes.
         * For password change or recovery use existing.
         * If kdf salt is null it will be generated.
         * @param kdfVersion system KDF version.
         * For password change or recovery use existing
         * @param accounts accounts to encrypt in specified order where first one is the root one
         * @param signers list of account signers, must include signer root account
         * @param verificationCode code from the invitation letter to skip the e-mail verification
         */
        @JvmStatic
        @JvmOverloads
        fun createWallet(
            email: String,
            password: CharArray,
            kdfAttributes: KdfAttributes,
            kdfVersion: Long,
            accounts: List<Account>,
            signers: Collection<SignerData>,
            walletType: String = WalletCreationData.TYPE_DEFAULT,
            verificationCode: String? = null,
        ): WalletCreationResult {
            val derivationEmail = email.toLowerCase()

            val kdfSalt = kdfAttributes.salt ?: WalletKeyDerivation.generateKdfSalt()
            val kdfAttributesWithSalt = kdfAttributes.copy()
            kdfAttributesWithSalt.salt = kdfSalt

            val walletKey = WalletKeyDerivation
                .deriveWalletEncryptionKey(derivationEmail, password, kdfAttributesWithSalt)
            val walletId = WalletKeyDerivation
                .deriveAndEncodeWalletId(derivationEmail, password, kdfAttributesWithSalt)

            val wallet = WalletCreationData(
                type = walletType,
                walletId = walletId,
                email = derivationEmail,
                accountId = accounts.first().accountId,
                keychainData = WalletEncryption.encryptAccountsV2(accounts, walletKey),
                salt = kdfSalt,
                verificationCode = verificationCode
            )

            wallet.relationships["kdf"] = WalletRelationship.kdf(kdfVersion)

            val passwordFactorAccount = Account.random()
            wallet.relationships["factor"] =
                WalletRelationship.passwordV2(
                    accountId = passwordFactorAccount.accountId,
                    keychainData = WalletEncryption.encryptAccountsV2(
                        listOf(
                            passwordFactorAccount
                        ), walletKey
                    ),
                    salt = kdfSalt
                )

            wallet.arrayRelationships["signers"] = signers.map(WalletRelationship.Companion::signer)

            return WalletCreationResult(
                creationData = wallet,
                accounts = accounts,
                rootAccount = accounts.first(),
                walletId = walletId,
                loginParams = LoginParams(
                    id = kdfVersion,
                    type = "kdf",
                    kdfAttributes = kdfAttributesWithSalt
                ),
                isVerified = false
            )
        }

        /**
         * @return completed wallet for sign up or update with one signer for root account
         * @param email user's email
         * @param password user's password
         * @param kdfAttributes system KDF attributes.
         * For password change or recovery use existing.
         * If kdf salt is null it will be generated.
         * @param kdfVersion system KDF version.
         * For password change or recovery use existing
         * @param verificationCode code from the invitation letter to skip the e-mail verification
         */
        @JvmStatic
        @JvmOverloads
        fun createWallet(
            email: String,
            password: CharArray,
            kdfAttributes: KdfAttributes,
            kdfVersion: Long,
            defaultSignerRole: Uint64,
            rootAccount: Account = Account.random(),
            walletType: String = WalletCreationData.TYPE_DEFAULT,
            verificationCode: String? = null,
        ): WalletCreationResult {
            return createWallet(
                email = email,
                password = password,
                kdfAttributes = kdfAttributes,
                kdfVersion = kdfVersion,
                accounts = listOf(rootAccount),
                walletType = walletType,
                signers = listOf(SignerData(rootAccount.accountId, defaultSignerRole)),
                verificationCode = verificationCode
            )
        }

        /**
         * @return completed wallet for sign up or update with one signer for root account
         *
         * @param email user's email
         * @param password user's password
         * @param kdfAttributes system KDF attributes.
         * For password change or recovery use existing.
         * If kdf salt is null it will be generated.
         * @param kdfVersion system KDF version.
         * For password change or recovery use existing
         * @param keyValueApi used to obtain default signer role ID
         * @param verificationCode code from the invitation letter to skip the e-mail verification
         */
        @JvmStatic
        @JvmOverloads
        fun createWallet(
            email: String,
            password: CharArray,
            kdfAttributes: KdfAttributes,
            kdfVersion: Long,
            keyValueApi: KeyValueStorageApiV3,
            rootAccount: Account = Account.random(),
            walletType: String = WalletCreationData.TYPE_DEFAULT,
            verificationCode: String? = null,
        ): ApiRequest<WalletCreationResult> {
            return MappedCallableApiRequest(
                {
                    val defaultSignerRole = getDefaultSignerRole(keyValueApi)
                    createWallet(
                        email, password, kdfAttributes, kdfVersion,
                        defaultSignerRole, rootAccount, walletType, verificationCode
                    )
                },
                { it }
            )
        }

        /**
         * Creates transaction for password change or recovery
         * that performs account signers update
         *
         * @param currentAccount account of a valid signer to be a tx signer
         * @param signersToRemove account IDs of signers that will be removed
         */
        fun createSignersUpdateTransaction(
            networkParams: NetworkParams,
            currentAccount: Account,
            originalAccountId: String,
            signersToAdd: Collection<SignerData>,
            signersToRemove: Collection<String>
        ): Transaction {
            val removeCurrentAccountSigner =
                if (signersToRemove.contains(currentAccount.accountId))
                    Operation.OperationBody.ManageSigner(
                        ManageSignerOp(
                            ManageSignerOp.ManageSignerOpData.Remove(
                                RemoveSignerData(
                                    publicKey = currentAccount.xdrPublicKey,
                                    ext = EmptyExt.EmptyVersion()
                                )
                            ),
                            EmptyExt.EmptyVersion()
                        )
                    )
                else
                    null

            val removeSignersButNoCurrent =
                signersToRemove.mapNotNull { signerToRemove ->
                    if (signerToRemove == currentAccount.accountId)
                        return@mapNotNull null

                    Operation.OperationBody.ManageSigner(
                        ManageSignerOp(
                            ManageSignerOp.ManageSignerOpData.Remove(
                                RemoveSignerData(
                                    publicKey =
                                    PublicKeyFactory.fromAccountId(signerToRemove),
                                    ext = EmptyExt.EmptyVersion()
                                )
                            ),
                            EmptyExt.EmptyVersion()
                        )
                    )
                }

            return TransactionBuilder(networkParams, originalAccountId)
                .addSigner(currentAccount)
                .addOperations(removeSignersButNoCurrent)
                .addOperations(signersToAdd.map { signerToAdd ->
                    Operation.OperationBody.ManageSigner(
                        ManageSignerOp(
                            ManageSignerOp.ManageSignerOpData.Create(
                                UpdateSignerData(
                                    publicKey =
                                    PublicKeyFactory.fromAccountId(signerToAdd.id),
                                    identity = signerToAdd.identity,
                                    weight = signerToAdd.weight,
                                    roleID = signerToAdd.roleId,
                                    details = signerToAdd.detailsJson ?: "{}",
                                    ext = EmptyExt.EmptyVersion()
                                )
                            ),
                            EmptyExt.EmptyVersion()
                        )
                    )
                })
                .apply { removeCurrentAccountSigner?.let { addOperation(it) } }
                .build()
        }

        private fun getDefaultSignerRole(keyValueApi: KeyValueStorageApiV3): Long {
            return try {
                keyValueApi
                    .getById(DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY)
                    .execute()
                    .get()
                    .value
                    .u32!!
            } catch (e: Exception) {
                throw IllegalStateException(
                    "Unable to obtain default role for signer " +
                            "by key $DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY", e
                )
            }
        }
    }
}