package org.tokend.sdk.api.v2.assets

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.assets.model.AssetResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AssetsServiceV2 {
    @GET("assets")
    @JvmSuppressWildcards
    fun getAssets(@QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<List<AssetResource>>>

    @GET("assets/{id}")
    @JvmSuppressWildcards
    fun getAssetById(@Path("id") accountId: String,
                     @QueryMap query: Map<String, Any>?): Call<JSONAPIDocument<AssetResource>>
}