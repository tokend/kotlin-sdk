package org.tokend.sdk.api.v3.transactions

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.TransactionResource
import org.tokend.sdk.api.v3.transactions.model.SubmitTransactionRequestBody
import retrofit2.Call
import retrofit2.http.*

interface TransactionsServiceV3 {
    @GET("v3/transactions")
    @JvmSuppressWildcards
    fun getTransactions(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<TransactionResource>>>

    @GET("v3/transactions/{id}")
    @JvmSuppressWildcards
    fun getTransactionById(@Path("id") id: String,
                           @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<TransactionResource>>

    @POST("v3/transactions")
    @JvmSuppressWildcards
    fun submitTransaction(@Body body: SubmitTransactionRequestBody): Call<JSONAPIDocument<TransactionResource>>
}