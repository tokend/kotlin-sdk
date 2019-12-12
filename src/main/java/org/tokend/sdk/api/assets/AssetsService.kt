package org.tokend.sdk.api.assets

import org.tokend.sdk.api.assets.model.AssetChartData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AssetsService {
    @GET("charts/{asset}")
    fun getChart(@Path("asset") asset: String): Call<AssetChartData>
}