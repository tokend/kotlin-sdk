package org.tokend.sdk.test.integration

import org.junit.Assert
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters
import org.tokend.sdk.api.identity.params.IdentitiesPageParams
import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.test.Util
import org.tokend.sdk.tfa.NeedTfaException
import org.tokend.sdk.tfa.TfaCallback
import org.tokend.sdk.tfa.TfaVerifier
import kotlin.math.absoluteValue
import kotlin.random.Random

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class IdentityTest {
    @Test
    fun aSetPhoneNumber() {
        var email = "setPhoneNumberTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, rootAccount)
                = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()
        email = wallet.attributes.email

        val currentWalletInfo = keyServer.getWalletInfo(email, password).execute().get()

        val tfaCallback = object : TfaCallback {
            override fun onTfaRequired(exception: NeedTfaException, verifierInterface: TfaVerifier.Interface) {
                if (exception.factorType != TfaFactor.Type.PHONE) {
                    verifierInterface.cancelVerification()
                    Assert.fail("TFA type ${TfaFactor.Type.PHONE} was expected, got ${exception.factorType}")
                } else {
                    verifierInterface.verify("000000", onError = {
                        verifierInterface.cancelVerification()
                        Assert.fail("Unable to verify default OTP")
                    })
                }
            }
        }
        val signedApi = Util.getSignedApi(rootAccount, currentWalletInfo.accountId, api.rootUrl, tfaCallback)

        val number = Random.nextInt(0, 999999999).toString()
        val phoneNumber = "+380${number.padEnd(9 - number.length, '0')}"

        signedApi
                .identities
                .setPhoneNumber(
                        currentWalletInfo.accountId,
                        phoneNumber
                )
                .execute()

        val remotePhoneNumber =
                api
                        .identities
                        .get(IdentitiesPageParams(phone = phoneNumber))
                        .execute()
                        .get()
                        .items
                        .firstOrNull()
                        ?.phoneNumber

        Assert.assertNotNull("There must be an identity record", remotePhoneNumber)
        Assert.assertEquals("Remote phone number must be equal to the sent one",
                phoneNumber, remotePhoneNumber)
    }

    @Test
    fun bSetSettings() {
        val key = "mypreference"
        val value = mapOf(
                "a1" to "test",
                "a2" to 42
        )

        var email = "setSettingsTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, rootAccount)
                = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()
        email = wallet.attributes.email

        val currentWalletInfo = keyServer.getWalletInfo(email, password).execute().get()

        val signedApi = Util.getSignedApi(rootAccount, currentWalletInfo.accountId, api.rootUrl)

        signedApi
                .identities
                .setSettingsItem(
                        currentWalletInfo.accountId,
                        key,
                        value
                )
                .execute()

        val remoteSettingsItem = signedApi
                .identities
                .getSettingsItemByKey(currentWalletInfo.accountId, key)
                .execute()
                .get()

        Assert.assertEquals(key, remoteSettingsItem.key)
        Assert.assertEquals(value["a1"], remoteSettingsItem.value["a1"].asText())
        Assert.assertEquals(value["a2"], remoteSettingsItem.value["a2"].asInt())

        val newValue = 0

        signedApi
                .identities
                .setSettingsItem(
                        currentWalletInfo.accountId,
                        key,
                        newValue
                )
                .execute()

        val updatedRemoteSettingsItem = signedApi
                .identities
                .getSettingsItemByKey(currentWalletInfo.accountId, key)
                .execute()
                .get()

        Assert.assertEquals(key, updatedRemoteSettingsItem.key)
        Assert.assertEquals(newValue, remoteSettingsItem.value.asInt())

        val pageFirstItem = signedApi
                .identities
                .getSettings(currentWalletInfo.accountId)
                .execute()
                .get()
                .items
                .first()

        Assert.assertEquals(updatedRemoteSettingsItem, pageFirstItem)
    }

    @Test
    fun cCreateAccount() {
        val email = "createAccountTest" + Random.nextInt().absoluteValue + "@mail.com"

        val api = Util.getApi()

        val createdAccountId = api.identities.create(email).execute().get().address
        val horizonAccountId = api.ingester.accounts.getById(createdAccountId).execute().get().id

        Assert.assertEquals(createdAccountId, horizonAccountId)
    }
}