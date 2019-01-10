package org.tokend.sdk.api.transactions

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.ErrorBody
import org.tokend.sdk.api.transactions.model.SubmitTransactionResponse
import org.tokend.sdk.api.transactions.model.TransactionFailedException
import org.tokend.sdk.factory.GsonFactory
import org.tokend.wallet.Transaction
import org.tokend.wallet.xdr.TransactionEnvelope
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.nio.charset.Charset

open class TransactionsApi(
        protected val transactionsService: TransactionsService
) {
    /**
     * Submits given transaction.
     * @see <a href="https://tokend.gitlab.io/docs/?http#submit-a-transaction">Docs</a>
     */
    open fun submit(transaction: Transaction) = submit(transaction.getEnvelope())

    /**
     * Submits given transaction envelope.
     * @see <a href="https://tokend.gitlab.io/docs/?http#submit-a-transaction">Docs</a>
     */
    open fun submit(transactionEnvelope: TransactionEnvelope) = submit(transactionEnvelope.toBase64())

    /**
     * Submits given transaction envelope.
     * @see <a href="https://tokend.gitlab.io/docs/?http#submit-a-transaction">Docs</a>
     */
    open fun submit(envelopeBase64: String): ApiRequest<SubmitTransactionResponse> {
        return SimpleRetrofitApiRequest(
                transactionsService.pushTransaction(envelopeBase64)
        ) { error ->
            (error as? HttpException)
                    ?.takeIf {
                        it.code() == HttpURLConnection.HTTP_BAD_REQUEST
                    }
                    ?.let {
                        getResponseFromHttpException(it)
                    }
                    ?.let {
                        TransactionFailedException(it)
                    }
                    ?: error
        }
    }

    protected open fun getResponseFromHttpException(
            httpException: HttpException
    ): SubmitTransactionResponse? {
        val response = httpException.response()
        val errorBody = response.errorBody()
        val buffer = errorBody.source().buffer().clone()
        val string = buffer.readString(Charset.defaultCharset())
        return try {
            // Elegant check for backward compatibility.
            // (I'm about to cut my throat tonight)
            if (response.headers().get("Content-Type") == LEGACY_ERROR_CONTENT_TYPE) {
                GsonFactory().getBaseGson().fromJson(string,
                        SubmitTransactionResponse::class.java)
            } else {
                val error = ErrorBody.fromJsonString(string).firstOrNull!!
                GsonFactory().getBaseGson().fromJson(error.meta,
                        SubmitTransactionResponse::class.java)
            }
        } catch (_: Exception) {
            null
        } finally {
            buffer.close()
        }
    }

    companion object {
        private const val LEGACY_ERROR_CONTENT_TYPE = "application/problem+json; charset=utf-8"
    }
}