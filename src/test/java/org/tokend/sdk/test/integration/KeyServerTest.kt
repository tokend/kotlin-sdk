package org.tokend.sdk.test.integration

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.keyserver.models.WalletCreateResult
import org.tokend.sdk.keyserver.models.WalletData
import org.tokend.sdk.keyserver.models.WalletRelation
import org.tokend.sdk.test.Util
import org.tokend.sdk.tfa.NeedTfaException
import org.tokend.sdk.tfa.PasswordTfaOtpGenerator
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.tfa.TfaVerifier
import org.tokend.wallet.Account
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

        val result = keyServer.createAndSaveWallet(email, password).execute().get()

        System.out.println("Account ID is ${result.rootAccount.accountId}")
        System.out.println("Recovery seed is ${result.recoveryAccount.secretSeed?.joinToString("")}")

        checkSignUpResult(email, password, result, keyServer)
    }

    @Test
    fun bSignUpWithAccounts() {
        val email = "signUpWithAccountsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val rootAccount = Account.random()
        val recoveryAccount = Account.random()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val result = keyServer.createAndSaveWallet(email, password, rootAccount, recoveryAccount)
                .execute().get()

        System.out.println("Account ID is ${result.rootAccount.accountId}")
        System.out.println("Recovery seed is ${result.recoveryAccount.secretSeed?.joinToString("")}")

        Assert.assertArrayEquals(
                "Result account must be equal to the provided one",
                rootAccount.secretSeed,
                result.rootAccount.secretSeed
        )

        Assert.assertArrayEquals(
                "Result recovery account must be equal to the provided one",
                recoveryAccount.secretSeed,
                result.recoveryAccount.secretSeed
        )

        checkSignUpResult(email, password, result, keyServer)
    }

    private fun checkSignUpResult(email: String,
                                  password: CharArray,
                                  result: WalletCreateResult,
                                  keyServer: KeyServer) {
        Assert.assertEquals(
                "Account ID in wallet data must be equal to the root account ID",
                result.rootAccount.accountId,
                result.walletData.attributes?.accountId
        )

        Assert.assertEquals(
                "Recovery relation account ID in wallet data must be equal to the recovery account ID",
                result.recoveryAccount.accountId,
                result.walletData.relationships[WalletRelation.RELATION_RECOVERY]
                        ?.data
                        ?.let { it as WalletData }
                        ?.attributes
                        ?.accountId
        )

        Assert.assertFalse(
                "Email for sign up test must contain upper case characters",
                email.toLowerCase() == email
        )

        Assert.assertTrue(
                "Email in wallet data must be lowercased",
                email.toLowerCase() == result.walletData.attributes?.email
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
    }

    @Test
    fun cPasswordChange() {
        var email = "passwordChangeTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, rootAccount, _)
                = keyServer.createAndSaveWallet(email, password).execute().get()
        email = wallet.attributes!!.email

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

        val signedApi = Util.getSignedApi(rootAccount, api.rootUrl, tfaCallback = tfaCallback)

        signedApi
                .users
                .create(rootAccount.accountId)
                .execute()

        val signers = signedApi
                .accounts
                .getSigners(rootAccount.accountId)
                .execute()
                .get()

        val netParams = api
                .general
                .getSystemInfo()
                .execute()
                .get()
                .toNetworkParams()


        val newPassword = "qweqwe".toCharArray()
        val newAccount = Account.random()

        val transaction = KeyServer.createSignersUpdateTransaction(
                netParams,
                rootAccount.accountId,
                rootAccount,
                signers,
                newAccount
        )

        val newWallet = KeyServer.createWallet(
                email = email,
                password = newPassword,
                rootAccount = newAccount,
                recoveryAccount = Account.random(),
                loginParams = keyServer.getLoginParams(email).execute().get()
        ).walletData

        newWallet.addTransactionRelation(transaction)

        val signedKeyServer = KeyServer(signedApi.wallets)
        signedKeyServer.updateWallet(wallet.id!!, newWallet).execute()

        val newWalletInfo = try {
            keyServer.getWalletInfo(email, newPassword).execute().get()
        } catch (e: Exception) {
            Assert.fail("New wallet must be accessible with new credentials")
            throw e
        }

        Assert.assertNotEquals(
                "Wallet ID must be changed after password change",
                wallet.id,
                newWalletInfo.walletIdHex
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
    }
}