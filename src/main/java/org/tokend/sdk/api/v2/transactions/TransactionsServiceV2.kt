package org.tokend.sdk.api.v2.transactions

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.transactions.model.TransactionResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface TransactionsServiceV2 {
    @GET("transactions")
    @JvmSuppressWildcards
    fun getTransactions(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<TransactionResource>>>

    @GET("offers/{id}")
    @JvmSuppressWildcards
    fun getTransactionById(@Path("id") id: String,
                           @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<TransactionResource>>
}