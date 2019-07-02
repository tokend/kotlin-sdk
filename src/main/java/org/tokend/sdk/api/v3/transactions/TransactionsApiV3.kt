package org.tokend.sdk.api.v3.transactions

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.model.ErrorBody
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.TransactionResource
import org.tokend.sdk.api.transactions.model.SubmitTransactionResponse
import org.tokend.sdk.api.transactions.model.TransactionFailedException
import org.tokend.sdk.api.v3.transactions.model.SubmitTransactionRequestBody
import org.tokend.sdk.api.v3.transactions.params.TransactionsPageParams
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.utils.extentions.isBadRequest
import org.tokend.wallet.Transaction
import org.tokend.wallet.xdr.TransactionEnvelope
import retrofit2.HttpException
import java.nio.charset.Charset

open class TransactionsApiV3(
        protected val transactionsService: TransactionsServiceV3
) {
    /**
     * @return transactions list page
     */
    open fun get(params: TransactionsPageParams? = null): ApiRequest<DataPage<TransactionResource>> {
        return MappedRetrofitApiRequest(
                transactionsService.getTransactions(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return transaction by it's ID
     */
    open fun getById(id: String): ApiRequest<TransactionResource> {
        return MappedRetrofitApiRequest(
                transactionsService.getTransactionById(
                        id,
                        emptyMap()
                ),
                JSONAPIDocument<TransactionResource>::get
        )
    }

    /**
     * Submits given transaction
     */
    open fun submit(transaction: Transaction,
                    waitForIngest: Boolean) = submit(transaction.getEnvelope(), waitForIngest)

    /**
     * Submits given transaction envelope
     */
    open fun submit(transactionEnvelope: TransactionEnvelope,
                    waitForIngest: Boolean) = submit(transactionEnvelope.toBase64(), waitForIngest)

    /**
     * Submits given transaction envelope
     */
    open fun submit(envelopeBase64: String,
                    waitForIngest: Boolean): ApiRequest<SubmitTransactionResponse> {
        return MappedRetrofitApiRequest(
                transactionsService.submitTransaction(
                        SubmitTransactionRequestBody(
                                envelopeBase64,
                                waitForIngest
                        )
                ),
                { document ->
                    val transaction = document.get()

                    SubmitTransactionResponse(
                            extras = null,
                            resultXdr = transaction.resultXdr,
                            envelopeXdr = transaction.envelopeXdr,
                            hash = transaction.hash,
                            ledger = transaction.ledgerSequence.toLong()
                    )
                },
                { error ->
                    (error as? HttpException)
                            ?.takeIf(HttpException::isBadRequest)
                            ?.let {
                                getResponseFromHttpException(it)
                            }
                            ?.let {
                                TransactionFailedException(it)
                            }
                            ?: error
                }
        )
    }

    private fun getResponseFromHttpException(httpException: HttpException
    ): SubmitTransactionResponse? {
        val response = httpException.response()
        val errorBody = response.errorBody()
        val buffer = errorBody.source().buffer().clone()
        val string = buffer.readString(Charset.defaultCharset())

        val gson = GsonFactory().getBaseGson()

        try {
            val firstError = gson.fromJson(string, ErrorBody::class.java).firstOrNull
                    ?: return null

            val meta = firstError.meta
                    ?: return null

            // Nothing to look at, move along.
            meta.asJsonObject.addProperty("envelope_xdr", meta.asJsonObject.get("envelope").asString)

            val extras = gson.fromJson(meta, SubmitTransactionResponse.Extras::class.java)

            return SubmitTransactionResponse(
                    extras = extras,
                    ledger = null,
                    hash = null,
                    envelopeXdr = extras.envelopeXdr,
                    resultXdr = extras.resultXdr
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            buffer.close()
        }
    }
}