package org.tokend.sdk.api.transactions

import okhttp3.ResponseBody
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.transactions.model.SubmitTransactionResponse
import org.tokend.sdk.api.transactions.model.TransactionFailedException
import org.tokend.sdk.factory.GsonFactory
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.nio.charset.Charset

class TransactionsApi(
        private val transactionsService: TransactionsService
) {
    fun postTransaction(envelopeBase64: String): ApiRequest<SubmitTransactionResponse> {
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

    private fun getResponseFromErrorBody(errorBody: ResponseBody): SubmitTransactionResponse? {
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