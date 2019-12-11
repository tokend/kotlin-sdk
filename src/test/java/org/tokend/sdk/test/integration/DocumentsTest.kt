package org.tokend.sdk.test.integration

import okhttp3.Request
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.documents.model.DocumentType
import org.tokend.sdk.factory.HttpClientFactory
import org.tokend.sdk.keyserver.KeyServer
import org.tokend.sdk.test.Util
import kotlin.math.absoluteValue
import kotlin.random.Random

class DocumentsTest {
    @Test
    fun aUploadDocument() {
        var email = "documentUploadTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, rootAccount)
                = keyServer.createAndSaveWallet(email, password, api.ingester.keyValue).execute().get()
        email = wallet.attributes.email

        val currentWalletInfo = keyServer.getWalletInfo(email, password).execute().get()

        val signedApi = Util.getSignedApi(rootAccount, currentWalletInfo.accountId, api.rootUrl)

        val document = "Hello World".toByteArray()
        val contentType = "text/plain"

        val uploadPolicy = signedApi
                .documents
                .requestUpload(
                        accountId = currentWalletInfo.accountId,
                        documentType = DocumentType.GENERAL_PRIVATE,
                        contentType = contentType
                )
                .execute()
                .get()

        signedApi
                .documents
                .upload(
                        policy = uploadPolicy,
                        contentType = contentType,
                        fileName = "test.txt",
                        content = document
                )
                .execute()

        val key = uploadPolicy["key"]
        if (key == null) {
            Assert.fail("Upload policy has no key, can't check if upload was successful")
            return
        }

        val uploadedUrl = signedApi
                .documents
                .getUrl(
                        documentKey = key
                )
                .execute()
                .get()

        val downloadedDocument =
                HttpClientFactory()
                        .getBaseHttpClientBuilder()
                        .build()
                        .newCall(
                                Request.Builder()
                                        .get()
                                        .url(uploadedUrl)
                                        .build()
                        )
                        .execute()
                        .body()
                        .bytes()

        Assert.assertArrayEquals("Downloaded must be the same as the uploaded one",
                document, downloadedDocument)
    }
}