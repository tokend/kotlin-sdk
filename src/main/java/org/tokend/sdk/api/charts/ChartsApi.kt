package org.tokend.sdk.api.charts

import org.tokend.sdk.api.charts.model.AssetChartData
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest

open class ChartsApi(
        protected val chartsService: ChartsService
) {
    /**
     * Will return [AssetChartData] for given asset code.
     */
    open fun getChart(assetCode: String): ApiRequest<AssetChartData> {
        return SimpleRetrofitApiRequest(
                chartsService.getChart(assetCode)
        )
    }

    /**
     * Will return [AssetChartData] for given asset pair.
     */
    open fun getChart(baseAsset: String,
                      quoteAsset: String): ApiRequest<AssetChartData> {
        return getChart("$baseAsset-$quoteAsset")
    }
}