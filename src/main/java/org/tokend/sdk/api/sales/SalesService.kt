package org.tokend.sdk.api.sales

import org.tokend.sdk.api.sales.model.SimpleSale
import org.tokend.sdk.api.responses.Page
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SalesService {
    @GET("sales")
    @JvmSuppressWildcards
    fun getSales(@QueryMap query: Map<String, Any>?): Call<Page<SimpleSale>>

    @GET("sales/{saleId}")
    fun getSale(@Path("saleId") id: Long): Call<SimpleSale>
}