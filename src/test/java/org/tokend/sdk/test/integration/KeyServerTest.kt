package org.tokend.sdk.test.integration

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.TokenDApi
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.api.wallets.model.EmailAlreadyTakenException
import org.tokend.sdk.api.wallets.model.InvalidCredentialsException
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.keyserver.models.SignerData
import org.tokend.sdk.keyserver.models.WalletCreateResult
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

        val result = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()

        System.out.println("Account ID is ${result.rootAccount.accountId}")

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

        val result = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue, rootAccount)
                .execute().get()

        System.out.println("Account ID is ${result.rootAccount.accountId}")

        Assert.assertArrayEquals(
                "Result account must be equal to the provided one",
                rootAccount.secretSeed,
                result.rootAccount.secretSeed
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
        val signerRole = api.ingester.keyValue
                .getById(KeyServer.DEFAULT_SIGNER_ROLE_KEY_VALUE_KEY)
                .map { it.value.u32!! }
                .execute()
                .get()

        val signerAccounts = mutableListOf(rootAccount, *(0..3).map { Account.random() }.toTypedArray())
        val signers = signerAccounts.map {
            SignerData(it.accountId, signerRole)
        }

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val result = keyServer.createAndSaveWallet(email, password, rootAccount, signers)
                .execute().get()

        System.out.println("Account ID is ${result.rootAccount.accountId}")

        Assert.assertArrayEquals(
                "Result account must be equal to the provided one",
                rootAccount.secretSeed,
                result.rootAccount.secretSeed
        )

        checkSignUpResult(email, password, result, api)

        val actualSigners = api.ingester.accounts
                .getAccountSigners(result.walletData.attributes.accountId)
                .execute()
                .get()

        Assert.assertTrue(
                "All signers must have specified role $signerRole",
                actualSigners.all { it.roles.map(BaseResource::getId).contains(signerRole.toString()) }
        )
        Assert.assertTrue(
                "All specified signers must be created",
                actualSigners
                        .map { signer -> signers.any { it.id == signer.id } }
                        .all { it }
        )
    }

    private fun checkSignUpResult(email: String,
                                  password: CharArray,
                                  result: WalletCreateResult,
                                  api: TokenDApi) {
        val keyServer = KeyServer(api.wallets)

        Assert.assertEquals(
                "Account ID in wallet data must be equal to the root account ID",
                result.rootAccount.accountId,
                result.walletData.attributes.accountId
        )

        Assert.assertFalse(
                "Email for sign up test must contain upper case characters",
                email.toLowerCase() == email
        )

        Assert.assertTrue(
                "Email in wallet data must be lowercased",
                email.toLowerCase() == result.walletData.attributes.email
        )

        val walletInfo = try {
            keyServer.getWalletInfo(email, password)
                    .execute()
                    .get()
        } catch (e: Exception) {
            Assert.fail("New wallet must be accessible")
            throw e
        }

        Assert.assertEquals(
                "Wallet ID obtained from the key server must be equal to the sent one",
                result.walletData.id,
                walletInfo.walletIdHex
        )

        val recoveryWalletInfo = try {
            keyServer.getWalletInfo(email, password, isRecovery = true)
                    .execute()
                    .get()
        } catch (e: Exception) {
            Assert.fail("Recovery wallet must be accessible")
            throw e
        }

        Assert.assertEquals(
                "Recovery wallet account ID must be equal to the original one",
                result.rootAccount.accountId,
                recoveryWalletInfo.accountId
        )

        val walletSigners = api.ingester.accounts
                .getAccountSigners(walletInfo.accountId)
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

        val (wallet, rootAccount)
                = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()
        email = wallet.attributes.email

        val currentWalletInfo = keyServer.getWalletInfo(email, password).execute().get()

        val tfaCallback = object : TfaCallback {
            override fun onTfaRequired(exception: NeedTfaException,
                                       verifierInterface: TfaVerifier.Interface) {
                if (exception.factorType == TfaFactor.Type.PASSWORD) {
                    verifierInterface.verify(
                            PasswordTfaOtpGenerator().generate(exception, email, password),
                            onError = { verifierInterface.cancelVerification() }
                    )
                }
            }
        }

        val signedApi = Util.getSignedApi(rootAccount, currentWalletInfo.accountId,
                api.rootUrl, tfaCallback = tfaCallback)

        val netParams = api
                .ingester
                .info
                .get()
                .execute()
                .get()
                .toNetworkParams()


        val newPassword = "qweqwe".toCharArray()
        val newAccount = Account.random()

        val signedKeyServer = KeyServer(signedApi.wallets)

        val newWalletInfo = signedKeyServer.updateWalletPassword(
                currentAccount = rootAccount,
                networkParams = netParams,
                newPassword = newPassword,
                newAccount = newAccount,
                currentWalletInfo = currentWalletInfo,
                accountsApi = api.ingester.accounts,
                keyValueApi = api.ingester.keyValue
        )
                .execute()
                .get()


        val remoteNewWalletInfo = try {
            keyServer.getWalletInfo(email, newPassword).execute().get()
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
                wallet.id,
                newWalletInfo.walletIdHex
        )

        Assert.assertFalse(
                "Wallet KDF salt must be changed after password change",
                currentWalletInfo.loginParams.kdfAttributes.salt!!.contentEquals(
                        remoteNewWalletInfo.loginParams.kdfAttributes.salt!!
                )
        )

        Assert.assertEquals(
                "Wallet account ID must be unchanged after password change",
                rootAccount.accountId,
                newWalletInfo.accountId
        )

        Assert.assertArrayEquals(
                "Wallet new secret seed must be equal to the required one",
                newAccount.secretSeed,
                newWalletInfo.secretSeed
        )

        val signers = api
                .ingester
                .accounts
                .getAccountSigners(currentWalletInfo.accountId)
                .execute()
                .get()

        Assert.assertTrue("A new signer ${newAccount.accountId} must be added to account signers",
                signers.any { signer ->
                    signer.id == newAccount.accountId
                })

        Assert.assertFalse("The old signer ${rootAccount.accountId} must be removed from account signers",
                signers.any { signer ->
                    signer.id == rootAccount.accountId
                })
    }

    @Test
    fun dSignUpTakenEmail() {
        val email = "signUpTakenEmailTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()

        try {
            keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()

            Assert.fail("${EmailAlreadyTakenException::class.java.name} expected")
        } catch (e: Exception) {
            if (e !is EmailAlreadyTakenException) {
                Assert.fail("${EmailAlreadyTakenException::class.java.name} expected " +
                        "but ${e::class.java.name} occurred")
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

        val (_, rootAccount) = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()

        val walletInfo = keyServer.getWalletInfo(email, password).execute().get()

        Assert.assertEquals("Remote wallet email must be " +
                "a lowercased current", email.toLowerCase(), walletInfo.email)
        Assert.assertEquals("Remote wallet account ID must be equal to the one used for sign up",
                rootAccount.accountId, walletInfo.accountId)
    }

    @Test
    fun fSignInInvalidCredentials() {
        val email = "signUpInvalidCredentialsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        keyServer.createAndSaveWallet(email, charArrayOf(), api.ingester.keyValue).execute().get()

        try {
            keyServer.getWalletInfo(email, "qweqwe".toCharArray()).execute().get()

            Assert.fail("${InvalidCredentialsException::class.java.name} expected")
        } catch (e: Exception) {
            if (e !is InvalidCredentialsException) {
                Assert.fail("${InvalidCredentialsException::class.java.name} expected " +
                        "but ${e::class.java.name} occurred")
            } else if (e.credential != InvalidCredentialsException.Credential.PASSWORD) {
                Assert.fail("Password was wrong but in the exception credential is ${e.credential}")
            }
        }

        try {
            keyServer.getWalletInfo(
                    SecureRandom.getSeed(12).encodeHexString(),
                    password
            ).execute().get()

            Assert.fail("${InvalidCredentialsException::class.java.name} expected")
        } catch (e: Exception) {
            if (e !is InvalidCredentialsException) {
                Assert.fail("${InvalidCredentialsException::class.java.name} expected " +
                        "but ${e::class.java.name} occurred")
            } else if (e.credential != InvalidCredentialsException.Credential.EMAIL) {
                Assert.fail("Email was wrong but in the exception credential is ${e.credential}")
            }
        }
    }
}