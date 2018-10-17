package org.tokend.sdk.api.transactions

import okhttp3.ResponseBody
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.api.transactions.model.SubmitTransactionResponse
import org.tokend.sdk.api.transactions.model.TransactionFailedException
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
    open fun submit(envelopeBase64: String): ApiRequest<SubmitTransactionResponse> {
        return SimpleRetrofitApiRequest(
                transactionsService.pushTransaction(envelopeBase64)
        ) { error ->
            (error as? HttpException)
                    ?.takeIf {
                        it.code() == HttpURLConnection.HTTP_BAD_REQUEST
                    }
                    ?.let {
                        getResponseFromErrorBody(it.response().errorBody())
                    }
                    ?.let {
                        TransactionFailedException(it)
                    }
                    ?: error
        }
    }

    protected open fun getResponseFromErrorBody(errorBody: ResponseBody): SubmitTransactionResponse? {
        val buffer = errorBody.source().buffer().clone()
        val string = buffer.readString(Charset.defaultCharset())
        return try {
            GsonFactory().getBaseGson().fromJson(string, SubmitTransactionResponse::class.java)
        } catch (e: Exception) {
            null
        } finally {
            buffer.close()
        }
    }
}