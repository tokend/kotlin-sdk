package org.tokend.sdk.test.jsonapi.integration

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.ingester.transactions.model.TransactionFailedException
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

        val netParams = api.ingester.info.get().execute().get().toNetworkParams()

        val tx = TransactionBuilder(netParams, Config.ADMIN_ACCOUNT.accountId)
                .addOperation(
                        Operation.OperationBody.RemoveRole(
                                RemoveRoleOp(
                                        roleID = 17044283,
                                        ext = RemoveRoleOp.RemoveRoleOpExt.EmptyVersion()
                                )
                        )
                )
                .addSigner(Config.ADMIN_ACCOUNT)
                .build()

        try {
            api.ingester.transactions.submit(tx, false).execute().get()
        } catch (e: TransactionFailedException) {
            Assert.assertEquals(TransactionFailedException.TX_FAILED, e.transactionResultCode)
            Assert.assertEquals(TransactionFailedException.OP_NOT_FOUND, e.firstFailedOperationResultCode)
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

        val systemInfo = api.ingester.info.get().execute().get()
        val netParams = systemInfo.toNetworkParams()

        val accountRole = 1L
        val signerRole = 1L

        val tx = TransactionBuilder(netParams, Config.ADMIN_ACCOUNT.accountId)
                .addOperation(
                        Operation.OperationBody.CreateAccount(
                                CreateAccountOp(
                                        destination = PublicKeyFactory.fromAccountId(
                                                Account.random().accountId
                                        ),
                                        roleIDs = arrayOf(accountRole),
                                        referrer = null,
                                        signersData = arrayOf(
                                                SignerData(
                                                        publicKey = PublicKeyFactory.fromAccountId(
                                                                Config.ADMIN_ACCOUNT.accountId
                                                        ),
                                                        roleIDs = arrayOf(signerRole),
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

        val result = api.ingester.transactions.submit(tx, false).execute().get()

        Assert.assertTrue("Transaction must be written to the new ledger",
                result.ledgerSequence > systemInfo.core.latest)
        Assert.assertNotNull("Result XDR can't be null", result.resultXdr)
        Assert.assertNotNull("Result meta XDR can't be null", result.resultMetaXdr)
        Assert.assertEquals("Transaction hash must be equal to the local one",
                tx.getHash().encodeHexString(), result.hash)
    }
}