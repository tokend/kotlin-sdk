package org.tokend.sdk.api.v2.fees

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.fees.model.ExactFeeResource
import org.tokend.sdk.api.v2.fees.model.FeeResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FeesServiceV2 {
    @GET("fees")
    @JvmSuppressWildcards
    fun getFees(@QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<List<FeeResource>>>

    @GET("fees/calculate")
    fun calculateFee(@QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<ExactFeeResource>>
}