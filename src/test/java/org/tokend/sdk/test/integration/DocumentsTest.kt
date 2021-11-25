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
        val email = "documentUploadTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, accounts)
                = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()

        val signedApi = Util.getSignedApi(accounts.first(), api.rootUrl)

        val fileName = "test.txt"
        val document = "Hello World".toByteArray()
        val contentType = "text/plain"

        val uploadPolicy = signedApi
                .documents
                .requestUpload(
                        accountId = wallet.accountId,
                        documentType = DocumentType.GENERAL_PRIVATE,
                        contentType = contentType
                )
                .execute()
                .get()

        val uploadedFile = api
                .documents
                .upload(
                        policy = uploadPolicy,
                        contentType = contentType,
                        fileName = fileName,
                        content = document
                )
                .execute()
                .get()

        val key = uploadPolicy["key"]
        if (key == null) {
            Assert.fail("Upload policy has no key, can't check if upload was successful")
            return
        }

        Assert.assertEquals(
                "Uploaded file key does not match the one from the policy",
                key,
                uploadedFile.key
        )
        Assert.assertEquals(
                "Uploaded file content type does not match the local one",
                contentType,
                uploadedFile.mimeType
        )
        Assert.assertEquals(
                "Uploaded file name does not match the local one",
                fileName,
                uploadedFile.name
        )

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