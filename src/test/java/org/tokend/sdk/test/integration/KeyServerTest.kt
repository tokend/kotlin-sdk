package org.tokend.sdk.test.integration

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.TokenDApi
import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.api.wallets.model.EmailAlreadyTakenException
import org.tokend.sdk.api.wallets.model.InvalidCredentialsException
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.keyserver.models.SignerData
import org.tokend.sdk.keyserver.models.WalletCreationResult
import org.tokend.sdk.test.Util
import org.tokend.sdk.tfa.NeedTfaException
import org.tokend.sdk.tfa.PasswordTfaOtpGenerator
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.tfa.TfaVerifier
import org.tokend.sdk.utils.extentions.encodeHexString
import org.tokend.sdk.utils.extentions.toNetworkParams
import org.tokend.wallet.Account
import java.security.SecureRandom
import kotlin.math.absoluteValue
import kotlin.random.Random

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class KeyServerTest {
    @Test
    fun aSignUpSimple() {
        val email = "signUpTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val result = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()

        System.out.println("Account ID is ${result.accounts.first().accountId}")

        checkSignUpResult(email, password, result, api)
    }

    @Test
    fun bSignUpWithAccount() {
        val email = "signUpWithAccountsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val rootAccount = Account.random()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val result = keyServer.createAndSaveWallet(email, password, api.v3.keyValue, rootAccount)
            .execute().get()

        System.out.println("Account ID is ${result.accounts.first().accountId}")

        Assert.assertArrayEquals(
            "Result account must be equal to the provided one",
            rootAccount.secretSeed,
            result.accounts.first().secretSeed
        )

        checkSignUpResult(email, password, result, api)
    }

    @Test
    fun bSignUpWithSigners() {
        val email = "signUpWithAccountsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()
        val keyServer = KeyServer(api.wallets)

        val rootAccount = Account.random()
        val signerRole = api.v3.keyValue
            .getById(KeyServer.DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY)
            .map { it.value.u32!! }
            .execute()
            .get()

        val signerAccounts =
            mutableListOf(rootAccount, *(0..3).map { Account.random() }.toTypedArray())
        val signers = signerAccounts.map {
            SignerData(
                id = it.accountId,
                roleId = signerRole
            )
        }

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val result = keyServer.createAndSaveWallet(email, password, rootAccount, signers)
            .execute().get()

        System.out.println("Account ID is ${result.accounts.first().accountId}")

        Assert.assertArrayEquals(
            "Result account must be equal to the provided one",
            rootAccount.secretSeed,
            result.accounts.first().secretSeed
        )

        checkSignUpResult(email, password, result, api)

        val actualSigners = api.v3
            .signers.get(result.dataToSave.accountId)
            .execute()
            .get()

        Assert.assertTrue(
            "All signers must have specified role $signerRole",
            actualSigners.all { it.role.id == signerRole.toString() }
        )
        Assert.assertTrue(
            "All specified signers must be created",
            actualSigners
                .map { signer -> signers.any { it.id == signer.id } }
                .all { it }
        )
    }

    private fun checkSignUpResult(
        email: String,
        password: CharArray,
        result: WalletCreationResult,
        api: TokenDApi
    ) {
        val keyServer = KeyServer(api.wallets)

        Assert.assertEquals(
            "Account ID in wallet data must be equal to the root account ID",
            result.accounts.first().accountId,
            result.dataToSave.accountId
        )

        Assert.assertFalse(
            "Email for sign up test must contain upper case characters",
            email.toLowerCase() == email
        )

        Assert.assertTrue(
            "Email in wallet data must be lowercased",
            email.toLowerCase() == result.dataToSave.email
        )

        val walletInfo = try {
            keyServer.getWallet(email, password)
                .execute()
                .get()
        } catch (e: Exception) {
            Assert.fail("New wallet must be accessible")
            throw e
        }

        Assert.assertEquals(
            "Wallet ID obtained from the key server must be equal to the sent one",
            result.dataToSave.walletId,
            walletInfo.walletId
        )

        val recoveryWalletInfo = try {
            keyServer.getWallet(email, password, isRecovery = true)
                .execute()
                .get()
        } catch (e: Exception) {
            Assert.fail("Recovery wallet must be accessible")
            throw e
        }

        Assert.assertEquals(
            "Recovery wallet account ID must be equal to the original one",
            result.accountId,
            recoveryWalletInfo.accountId
        )

        val walletSigners = api.v3.signers
            .get(walletInfo.accountId)
            .execute()
            .get()

        Assert.assertTrue(
            "There must be a signer for wallet root account",
            walletSigners.any { it.id == walletInfo.accountId }
        )
    }

    @Test
    fun cPasswordChange() {
        var email = "passwordChangeTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, accounts)
                = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()
        email = wallet.email

        val currentWalletInfo = keyServer.getWallet(email, password).execute().get()

        val tfaCallback = object : TfaCallback {
            override fun onTfaRequired(
                exception: NeedTfaException,
                verifierInterface: TfaVerifier.Interface
            ) {
                if (exception.factorType == TfaFactor.Type.PASSWORD) {
                    verifierInterface.verify(
                        PasswordTfaOtpGenerator().generate(exception, email, password),
                        onError = { verifierInterface.cancelVerification() }
                    )
                }
            }
        }

        val signedApi = Util.getSignedApi(accounts.first(), api.rootUrl, tfaCallback = tfaCallback)

        val netParams = api.v3.info.getInfo()
            .execute()
            .get()
            .toNetworkParams()

        val newPassword = "qweqwe".toCharArray()
        val newAccount = Account.random()

        val signedKeyServer = KeyServer(signedApi.wallets)

        val newWalletInfo = signedKeyServer.updateWalletPassword(
            currentAccount = accounts.first(),
            networkParams = netParams,
            newPassword = newPassword,
            newAccount = newAccount,
            currentWallet = currentWalletInfo,
            signersApi = api.v3.signers,
            keyValueApi = api.v3.keyValue
        )
            .execute()
            .get()


        val remoteNewWalletInfo = try {
            keyServer.getWallet(email, newPassword).execute().get()
        } catch (e: Exception) {
            Assert.fail("New wallet must be accessible with new credentials")
            throw e
        }

        Assert.assertEquals(
            "Updated info from the key server must be equal to the returned one",
            newWalletInfo,
            remoteNewWalletInfo
        )

        Assert.assertNotEquals(
            "Wallet ID must be changed after password change",
            wallet.walletId,
            newWalletInfo.walletId
        )

        Assert.assertFalse(
            "Wallet KDF salt must be changed after password change",
            currentWalletInfo.loginParams.kdfAttributes.salt!!.contentEquals(
                remoteNewWalletInfo.loginParams.kdfAttributes.salt!!
            )
        )

        Assert.assertEquals(
            "Wallet account ID must be unchanged after password change",
            accounts.first().accountId,
            newWalletInfo.accountId
        )

        Assert.assertEquals(
            "Wallet new account seed must be equal to the required one",
            newAccount,
            newWalletInfo.accounts.first()
        )

        val signers = api
            .v3
            .signers
            .get(currentWalletInfo.accountId)
            .execute()
            .get()

        Assert.assertTrue("A new signer ${newAccount.accountId} must be added to account signers",
            signers.any { signer ->
                signer.id == newAccount.accountId
            })

        Assert.assertFalse("The old signer ${accounts.first().accountId} must be removed from account signers",
            signers.any { signer ->
                signer.id == accounts.first().accountId
            })
    }

    @Test
    fun dSignUpTakenEmail() {
        val email = "signUpTakenEmailTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()

        try {
            keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()

            Assert.fail("${EmailAlreadyTakenException::class.java.name} expected")
        } catch (e: Exception) {
            if (e !is EmailAlreadyTakenException) {
                Assert.fail(
                    "${EmailAlreadyTakenException::class.java.name} expected " +
                            "but ${e::class.java.name} occurred"
                )
            }
        }
    }

    @Test
    fun eSignIn() {
        val email = "signInTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (_, accounts) = keyServer.createAndSaveWallet(email, password, api.v3.keyValue)
            .execute().get()

        val walletInfo = keyServer.getWallet(email, password).execute().get()

        Assert.assertEquals(
            "Remote wallet email must be " +
                    "a lowercased current", email.toLowerCase(), walletInfo.login
        )
        Assert.assertEquals(
            "Remote wallet account ID must be equal to the one used for sign up",
            accounts.first().accountId,
            walletInfo.accountId
        )
    }

    @Test
    fun fSignInInvalidCredentials() {
        val email = "signUpInvalidCredentialsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        keyServer.createAndSaveWallet(email, charArrayOf(), api.v3.keyValue).execute().get()

        try {
            keyServer.getWallet(email, "qweqwe".toCharArray()).execute().get()

            Assert.fail("${InvalidCredentialsException::class.java.name} expected")
        } catch (e: Exception) {
            if (e !is InvalidCredentialsException) {
                Assert.fail(
                    "${InvalidCredentialsException::class.java.name} expected " +
                            "but ${e::class.java.name} occurred"
                )
            } else if (e.credential != InvalidCredentialsException.Credential.PASSWORD) {
                Assert.fail("Password was wrong but in the exception credential is ${e.credential}")
            }
        }

        try {
            keyServer.getWallet(
                SecureRandom.getSeed(12).encodeHexString(),
                password
            ).execute().get()

            Assert.fail("${InvalidCredentialsException::class.java.name} expected")
        } catch (e: Exception) {
            if (e !is InvalidCredentialsException) {
                Assert.fail(
                    "${InvalidCredentialsException::class.java.name} expected " +
                            "but ${e::class.java.name} occurred"
                )
            } else if (e.credential != InvalidCredentialsException.Credential.EMAIL) {
                Assert.fail("Email was wrong but in the exception credential is ${e.credential}")
            }
        }
    }

    @Test
    fun gSignUpManyAccounts() {
        val accounts = (1..3).map { Account.random() }

        val email = "signUpInvalidCredentialsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val signerRole = api.v3.keyValue
            .getById(KeyServer.DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY)
            .map { it.value.u32!! }
            .execute()
            .get()

        val signers = accounts.map {
            SignerData(it.accountId, roleId = signerRole)
        }

        val result = keyServer.createAndSaveWallet(
            email = email,
            password = password,
            accounts = accounts,
            signers = signers
        )
            .execute()
            .get()

        System.out.println("Account ID is ${result.accounts.first().accountId}")

        Assert.assertArrayEquals(
            "Root account must be equal to the first one",
            accounts.first().secretSeed,
            result.accounts.first().secretSeed
        )
        accounts.forEachIndexed { i, account ->
            Assert.assertEquals(
                "Result wallet secret seed #$i must be equal to the local one",
                account.accountId,
                result.accounts[i].accountId
            )
        }

        checkSignUpResult(email, password, result, api)

        val actualSigners = api.v3
            .signers.get(result.dataToSave.accountId)
            .execute()
            .get()

        Assert.assertTrue(
            "All signers must have specified role $signerRole",
            actualSigners.all { it.role.id == signerRole.toString() }
        )
        Assert.assertTrue(
            "All specified signers must be created",
            actualSigners
                .map { signer -> signers.any { it.id == signer.id } }
                .all { it }
        )

        val walletInfo = keyServer.getWallet(email, password)
            .execute()
            .get()

        accounts.forEachIndexed { i, account ->
            Assert.assertEquals(
                "Remote wallet account #$i must be equal to the local one",
                account,
                walletInfo.accounts[i]
            )
        }
    }
}