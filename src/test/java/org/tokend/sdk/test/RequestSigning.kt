package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.signing.AccountRequestSigner
import org.tokend.sdk.signing.RequestSigningInterceptor
import org.tokend.wallet.Account

class RequestSigning {
    @Test
    fun regularRequestSigning() {
        val seed = "SBU3OI7Q5EUUSEA4CUWLEHMMJDLQBO3P6UHFF53BCLUP6FIVKXYAJG2D".toCharArray()
        val account = Account.fromSecretSeed(seed)
        val signer = AccountRequestSigner(account.accountId, account)
        val url = "test/url/for/signing?include=balances"
        val date = "Tue, 17 Dec 2019 11:49:36 GMT"

        val expectedSignature = "keyId=\"GBC3DQFW3L6R4SNENV7AFZQBF2XNKUWSLEPVSQA63J3TQN5EKMZXS2T6\",algorithm=\"ed25519-sha256\",signature=\"8g5+yEzVSdNLA7k8I0a6HJdEYDppSSEWTIK08cbeXU6DwQK+ipSrYmMXx8ekaMUuHoHfRxfYkI2TaSfrrlSFDg==\",headers=\"account date real-request-target\""
        val expectedTarget = "get /test/url/for/signing?include=balances"

        val headers = RequestSigningInterceptor.getSignatureHeaders(signer, "GET", url, date)

        Assert.assertEquals(account.accountId, headers[RequestSigningInterceptor.ACCOUNT_HEADER])
        Assert.assertEquals(expectedSignature, headers[RequestSigningInterceptor.SIGNATURE_HEADER])
        Assert.assertEquals(date, headers[RequestSigningInterceptor.DATE_HEADER])
        Assert.assertEquals(expectedTarget, headers[RequestSigningInterceptor.REAL_REQUEST_TARGET_HEADER])
    }

    @Test
    fun differentAccountSigner() {
        val accountId = "GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB"
        val seed = "SBU3OI7Q5EUUSEA4CUWLEHMMJDLQBO3P6UHFF53BCLUP6FIVKXYAJG2D".toCharArray()
        val account = Account.fromSecretSeed(seed)
        val signer = AccountRequestSigner(accountId, account)
        val url = "test/url/for/signing?include=balances"

        val headers = RequestSigningInterceptor.getSignatureHeaders(signer, "GET", url)

        Assert.assertEquals(accountId, headers[RequestSigningInterceptor.ACCOUNT_HEADER])
    }
}