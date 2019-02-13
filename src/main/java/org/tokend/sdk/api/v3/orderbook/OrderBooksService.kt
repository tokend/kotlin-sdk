package org.tokend.sdk.api.v3.orderbook

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.OrderBookEntryResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface OrderBooksService {
    @GET("order_books/{id}")
    @JvmSuppressWildcards
    fun getById(@Path("id") id: String,
                @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<OrderBookEntryResource>>>
}