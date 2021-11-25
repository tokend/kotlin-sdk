package org.tokend.sdk.api.v3.atomicswap

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.AtomicSwapAskResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AtomicSwapsService {
    @GET("v3/atomic_swap_asks")
    @JvmSuppressWildcards
    fun getAtomicSwapAsks(@QueryMap query: Map<String, Any>):
            Call<JSONAPIDocument<List<AtomicSwapAskResource>>>

    @GET("v3/atomic_swap_asks/{id}")
    @JvmSuppressWildcards
    fun getAtomicSwapAskById(@Path("id") id: String,
                             @QueryMap query: Map<String, Any>):
            Call<JSONAPIDocument<AtomicSwapAskResource>>
}