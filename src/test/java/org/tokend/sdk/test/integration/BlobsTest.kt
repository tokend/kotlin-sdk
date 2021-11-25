package org.tokend.sdk.test.integration

import com.fasterxml.jackson.annotation.JsonProperty
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.blobs.model.BlobType
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.test.Util
import java.math.BigDecimal
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

        val (wallet, accounts)
                = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()
        email = wallet.email

        val currentWalletInfo = keyServer.getWallet(email, password).execute().get()

        val signedApi = Util.getSignedApi(accounts.first(), api.rootUrl)

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

        Assert.assertEquals(
            "Downloaded blob content must be the same as the uploaded one",
            content, downloadedBlob.valueString
        )
    }

    data class DataClass(
        @JsonProperty("a")
        val propertyA: String,
        @JsonProperty("b")
        val propertyB: BigDecimal
    )

    @Test
    fun bCreateBlobWithObjectValue() {
        var email = "blobCreationTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, accounts)
                = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()
        email = wallet.email

        val currentWalletInfo = keyServer.getWallet(email, password).execute().get()

        val signedApi = Util.getSignedApi(accounts.first(), api.rootUrl)

        val content = DataClass(
            propertyA = "Oleg",
            propertyB = BigDecimal("3.14")
        )

        val blobToCreate = Blob(BlobType.ALPHA, content)

        Assert.assertEquals(
            "The content must be serialized properly",
            "{\"a\":\"Oleg\",\"b\":\"3.14\"}",
            blobToCreate.valueString
        )
        val blob = signedApi
            .blobs
            .create(
                ownerAccountId = currentWalletInfo.accountId,
                blob = blobToCreate
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

        Assert.assertEquals(
            "Downloaded blob value must be deserialized properly",
            content, downloadedBlob.getValue(DataClass::class.java)
        )
    }
}