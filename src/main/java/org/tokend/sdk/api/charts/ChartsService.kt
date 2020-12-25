package org.tokend.sdk.api.charts

import org.tokend.sdk.api.charts.model.AssetChartData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ChartsService {
    @GET("charts/{asset}")
    fun getChart(@Path("asset") asset: String): Call<AssetChartData>
}