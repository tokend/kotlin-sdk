package org.tokend.sdk.api.v3.sales

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.SaleResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SalesServiceV3 {
    @GET("v3/sales")
    @JvmSuppressWildcards
    fun getSales(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SaleResource>>>

    @GET("v3/sales/{id}")
    @JvmSuppressWildcards
    fun getSaleById(@Path("id") id: String,
                    @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<SaleResource>>

    @GET("v3/accounts/{id}/sales")
    @JvmSuppressWildcards
    fun getSalesForAccount(@Path("id") accountId: String,
                           @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SaleResource>>>

    @GET("v3/accounts/{id}/sales/{sale_id}")
    @JvmSuppressWildcards
    fun getSaleByIdForAccount(@Path("id") accountId: String,
                              @Path("sale_id") saleId: String,
                              @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<SaleResource>>
}