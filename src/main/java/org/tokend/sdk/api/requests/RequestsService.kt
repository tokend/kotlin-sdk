package org.tokend.sdk.api.requests

import org.tokend.sdk.api.base.model.Page
import org.tokend.sdk.api.requests.model.SimpleReviewableRequest
import org.tokend.sdk.api.requests.model.asset.SimpleAssetReviewableRequest
import org.tokend.sdk.api.requests.model.preissuance.PreIssuanceReviewableRequest
import org.tokend.sdk.api.requests.model.sale.SimpleSaleReviewableRequest
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface RequestsService {
    @JvmSuppressWildcards
    @GET("requests")
    fun getAll(@QueryMap query: Map<String, Any>): Call<Page<SimpleReviewableRequest>>

    @JvmSuppressWildcards
    @GET("request/assets")
    fun getAssets(@QueryMap query: Map<String, Any>): Call<Page<SimpleAssetReviewableRequest>>

    @JvmSuppressWildcards
    @GET("request/sales")
    fun getSales(@QueryMap query: Map<String, Any>): Call<Page<SimpleSaleReviewableRequest>>

    @JvmSuppressWildcards
    @GET("request/preissuances")
    fun getPreIssuances(@QueryMap query: Map<String, Any>): Call<Page<PreIssuanceReviewableRequest>>
}