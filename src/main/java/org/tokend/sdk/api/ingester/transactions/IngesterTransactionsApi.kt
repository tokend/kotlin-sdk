package org.tokend.sdk.api.ingester.transactions

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.model.ErrorBody
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.TransactionResource
import org.tokend.sdk.api.ingester.transactions.model.TransactionFailedException
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.api.ingester.transactions.model.SubmitTransactionRequestBody
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.utils.extentions.isBadRequest
import org.tokend.wallet.Transaction
import org.tokend.wallet.xdr.TransactionEnvelope
import retrofit2.HttpException
import java.nio.charset.Charset

open class IngesterTransactionsApi(
        protected open val requests: CustomRequestsApi,
        protected open val transactionsService: IngesterTransactionsService
) {
    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<TransactionResource>> {
        return requests.getPage(
                url = "horizon/transactions",
                pageItemClass = TransactionResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<TransactionResource> {
        return requests.get(
                url = "horizon/transactions/$id",
                responseClass = TransactionResource::class.java,
                queryMap = params.map()
        )
    }

    open fun submit(transaction: Transaction,
                    waitForIngest: Boolean) = submit(transaction.getEnvelope(), waitForIngest)

    open fun submit(transactionEnvelope: TransactionEnvelope,
                    waitForIngest: Boolean) = submit(transactionEnvelope.toBase64(), waitForIngest)

    open fun submit(envelopeBase64: String,
                    waitForIngest: Boolean): ApiRequest<TransactionResource> {
        return MappedRetrofitApiRequest(
                transactionsService.submit(
                        SubmitTransactionRequestBody(envelopeBase64, waitForIngest)
                ),
                JSONAPIDocument<TransactionResource>::get
        ) { error ->
            (error as? HttpException)
                    ?.takeIf(HttpException::isBadRequest)
                    ?.let {
                        getTxExceptionFromHttpException(it)
                    }
                    ?: error
        }
    }

    protected open fun getTxExceptionFromHttpException(httpException: HttpException)
            : TransactionFailedException? {
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

            val resultCodes = meta.asJsonObject["result_codes"]?.asJsonObject
                    ?: return null

            val transactionResult = resultCodes["transaction"]?.asString
                    ?: return null

            val operationResultCodes = resultCodes["operations"]?.asJsonArray
                    ?.map { it.asString }
                    ?: return null

            val message = resultCodes["messages"]
                    ?.takeIf { it.isJsonArray }
                    ?.asJsonArray
                    ?.mapNotNull { it.asString }
                    ?.joinToString()

            return TransactionFailedException(transactionResult, operationResultCodes, message)
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        } finally {
            buffer.close()
        }
    }
}