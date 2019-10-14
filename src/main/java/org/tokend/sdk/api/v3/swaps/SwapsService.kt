package org.tokend.sdk.api.v3.swaps

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.SwapResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SwapsService {
    @GET("v3/swaps")
    @JvmSuppressWildcards
    fun getSwapsPage(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SwapResource>>>

    @GET("v3/swaps/{id}")
    @JvmSuppressWildcards
    fun getSwap(@Path("id") id: String,
                @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<SwapResource>>
}