package org.tokend.sdk.api.v3.orderbook

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.MatchResource
import org.tokend.sdk.api.generated.resources.OrderBookEntryResource
import org.tokend.sdk.api.generated.resources.OrderBookResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface OrderBooksService {
    @GET("v3/order_book/{id}")
    @JvmSuppressWildcards
    fun getById(@Path("id") id: String,
                @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<OrderBookEntryResource>>>

    @GET("v3/order_books/{id}")
    @JvmSuppressWildcards
    fun getByIdNew(@Path("id") id: String,
                   @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<OrderBookResource>>

    @GET("v3/matches")
    @JvmSuppressWildcards
    fun getMatches(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<MatchResource>>>
}