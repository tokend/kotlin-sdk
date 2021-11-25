package org.tokend.sdk.test.integration

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.blobs.model.BlobType
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.test.Util
import kotlin.math.absoluteValue
import kotlin.random.Random

class BlobsTest {
    @Test
    fun aCreateBlob() {
        var email = "blobCreationTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, rootAccount)
                = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()
        email = wallet.email

        val currentWalletInfo = keyServer.getWallet(email, password).execute().get()

        val signedApi = Util.getSignedApi(rootAccount, api.rootUrl)

        val content = "Hello World"

        val blob = signedApi
                .blobs
                .create(
                        ownerAccountId = currentWalletInfo.accountId,
                        blob = Blob(BlobType.ALPHA, content)
                )
                .execute()
                .get()

        val downloadedBlob = signedApi
                .blobs
                .getBlob(
                        blobId = blob.id
                )
                .execute()
                .get()

        Assert.assertEquals("Downloaded blob content must be the same as the uploaded one",
                content, downloadedBlob.valueString)
    }
}