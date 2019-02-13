package org.tokend.sdk.api.v3.offers

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.OfferResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface OffersServiceV3 {
    @GET("offers")
    @JvmSuppressWildcards
    fun getOffers(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<OfferResource>>>

    @GET("offers/{id}")
    @JvmSuppressWildcards
    fun getOfferById(@Path("id") id: String,
                     @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<OfferResource>>
}