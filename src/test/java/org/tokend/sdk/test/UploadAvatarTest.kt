package org.tokend.sdk.test

import org.junit.Test
import org.tokend.sdk.api.documents.model.DocumentType
import org.tokend.sdk.keyserver.KeyServer
import java.io.File
import kotlin.math.absoluteValue
import kotlin.random.Random

class UploadAvatarTest {
    @Test
    fun avatarTest(){
        val email = "documentUploadTest" + Random.nextInt().absoluteValue + "@mail.com"
        val password = "qwe123".toCharArray()

        val api = Util.getApi()

        val keyServer = KeyServer(api.wallets)

        System.out.println("Attempt to sign up $email ${password.joinToString("")}")

        val (wallet, rootAccount)
                = keyServer.createAndSaveWallet(email, password, api.v3.keyValue).execute().get()

        val signedApi = Util.getSignedApi(rootAccount, api.rootUrl)
        val uploadPolicy = signedApi
                .documents
                .requestUpload(
                        accountId = wallet.attributes.accountId,
                        documentType = DocumentType.KYC_SELFIE,
                        contentType = "image/jpeg"
                )
                .execute()
                .get()

        //signedApi.documents.upload(uploadPolicy, File()
    }
}