package org.tokend.sdk.api.v3.fees

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.CalculatedFeeResource
import org.tokend.sdk.api.v3.model.generated.resources.FeeRecordResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface FeesServiceV3 {
    @GET("v3/fees")
    @JvmSuppressWildcards
    fun getFees(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<FeeRecordResource>>>

    @GET("v3/accounts/{id}/calculated_fees")
    @JvmSuppressWildcards
    fun getCalculatedFee(@Path("id") accountId: String,
            @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<CalculatedFeeResource>>
}