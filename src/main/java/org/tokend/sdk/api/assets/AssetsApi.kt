package org.tokend.sdk.api.assets

import org.tokend.sdk.api.assets.model.AssetChartData
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest

open class AssetsApi(
        protected val assetsService: AssetsService
) {
    /**
     * Will return [AssetChartData] for given asset code.
     */
    open fun getChart(assetCode: String): ApiRequest<AssetChartData> {
        return SimpleRetrofitApiRequest(
                assetsService.getChart(assetCode)
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