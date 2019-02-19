package org.tokend.sdk.api.v3.assetpairs

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.AssetPairResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AssetPairsService {
    @GET("v3/asset_pairs")
    @JvmSuppressWildcards
    fun getAssetPairs(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<AssetPairResource>>>

    @GET("v3/asset_pairs/{id}")
    @JvmSuppressWildcards
    fun getAssetPairById(@Path("id") id: String,
                         @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<AssetPairResource>>
}