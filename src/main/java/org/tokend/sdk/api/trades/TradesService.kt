package org.tokend.sdk.api.trades

import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.api.base.model.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface TradesService {
    @GET("order_book")
    @JvmSuppressWildcards
    fun getOrderBook(@QueryMap query: Map<String, Any>): Call<Page<Offer>>

    @GET("trades")
    fun getMatchedOrders(@QueryMap query: Map<String, Any>): Call<Page<Offer>>
}