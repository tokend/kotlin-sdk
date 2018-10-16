package org.tokend.sdk.api.assets

import org.tokend.sdk.api.assets.model.ConvertAssetsResponse
import org.tokend.sdk.api.assets.model.AssetChartData
import org.tokend.sdk.api.assets.model.AssetPair
import org.tokend.sdk.api.assets.model.SimpleAsset
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AssetsService {
    @GET("asset_pairs")
    fun getAssetPairs(): Call<List<AssetPair>>

    @GET("asset_pairs/convert")
    fun convertAmount(@Query("source_asset") sourceAsset: String?,
                      @Query("dest_asset") destAsset: String?,
                      @Query("amount") amount: String?): Call<ConvertAssetsResponse>

    @GET("assets/{asset}")
    fun getAsset(@Path("asset") asset: String): Call<SimpleAsset>

    @GET("assets")
    fun getAssets(): Call<List<SimpleAsset>>

    @GET("charts/{asset}")
    fun getChart(@Path("asset") asset: String): Call<AssetChartData>
}