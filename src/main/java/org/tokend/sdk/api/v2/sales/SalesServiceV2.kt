package org.tokend.sdk.api.v2.sales

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.sales.model.SaleResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SalesServiceV2 {
    @GET("sales")
    @JvmSuppressWildcards
    fun getSales(@QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<List<SaleResource>>>

    @GET("sales/{id}")
    @JvmSuppressWildcards
    fun getSaleById(@Path("id") id: String,
                    @QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<SaleResource>>
}