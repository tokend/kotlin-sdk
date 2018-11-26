package org.tokend.sdk.api.fees

import org.tokend.sdk.api.fees.model.Fee
import org.tokend.sdk.api.fees.model.FeesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface FeesService {
    @GET("fees/{feeType}")
    @JvmSuppressWildcards
    fun getFee(@Path("feeType") feeType: Int,
               @QueryMap query: Map<String, Any>?): Call<Fee>

    @GET("fees")
    fun getFees(): Call<FeesResponse>
}