package org.tokend.sdk.test.jsonapi.integration

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.transactions.model.TransactionFailedException
import org.tokend.sdk.test.Config
import org.tokend.sdk.test.Util
import org.tokend.sdk.utils.extentions.encodeHexString
import org.tokend.sdk.utils.extentions.toNetworkParams
import org.tokend.wallet.Account
import org.tokend.wallet.PublicKeyFactory
import org.tokend.wallet.TransactionBuilder
import org.tokend.wallet.xdr.*

class TransactionsIntegrationTest {
    @Test
    fun submitError() {
        val api = Util.getApi()

        val netParams = api.v3.general.getSystemInfo().execute().get().toNetworkParams()

        val tx = TransactionBuilder(netParams, Config.ADMIN_ACCOUNT.accountId)
                .addOperation(
                        Operation.OperationBody.CreateAtomicSwapBidRequest(
                                CreateAtomicSwapBidRequestOp(
                                        request = CreateAtomicSwapBidRequest(
                                                askID = 404,
                                                creatorDetails = "{}",
                                                baseAmount = 1,
                                                quoteAsset = "OLE",
                                                ext = CreateAtomicSwapBidRequest.CreateAtomicSwapBidRequestExt.EmptyVersion()
                                        ),
                                        ext = CreateAtomicSwapBidRequestOp.CreateAtomicSwapBidRequestOpExt.EmptyVersion()
                                )
                        )
                )
                .addSigner(Config.ADMIN_ACCOUNT)
                .build()

        try {
            api.v3.transactions.submit(tx, false).execute().get()
        } catch (e: TransactionFailedException) {
            Assert.assertEquals(TransactionFailedException.TX_FAILED, e.transactionResultCode)
            Assert.assertEquals(TransactionFailedException.OP_NO_ENTRY, e.firstOperationResultCode)
            return
        } catch (e: Exception) {
            Assert.fail("Got $e but TransactionFailedException is expected")
            return
        }

        Assert.fail("Got no exception but TransactionFailedException is expected")
    }

    @Test
    fun submit() {
        val api = Util.getApi()

        val netParams = api.general.getSystemInfo().execute().get().toNetworkParams()

        val accountRole = api.v3.accounts.getRoles().execute().get().items.first().id.toLong()
        val signerRole = api.v3.signers.getRoles().execute().get().items.first().id.toLong()

        val tx = TransactionBuilder(netParams, Config.ADMIN_ACCOUNT.accountId)
                .addOperation(
                        Operation.OperationBody.CreateAccount(
                                CreateAccountOp(
                                        destination = PublicKeyFactory.fromAccountId(
                                                Account.random().accountId
                                        ),
                                        roleID = accountRole,
                                        referrer = null,
                                        signersData = arrayOf(
                                                UpdateSignerData(
                                                        publicKey = PublicKeyFactory.fromAccountId(
                                                                Config.ADMIN_ACCOUNT.accountId
                                                        ),
                                                        roleID = signerRole,
                                                        weight = 1000,
                                                        identity = 0,
                                                        details = "{}",
                                                        ext = EmptyExt.EmptyVersion()
                                                )
                                        ),
                                        ext = CreateAccountOp.CreateAccountOpExt.EmptyVersion()
                                )
                        )
                )
                .addSigner(Config.ADMIN_ACCOUNT)
                .build()

        val result = api.v3.transactions.submit(tx, false).execute().get()

        Assert.assertTrue("Transaction must be successful", result.isSuccess)
        Assert.assertNotNull("Envelope XDR can't be null", result.getEnvelopeXdr())
        Assert.assertNotNull("Result meta XDR can't be null", result.resultMetaXdr)
        Assert.assertEquals("Transaction hash must be equal to the local one",
                tx.getHash().encodeHexString(), result.hash)
    }
}