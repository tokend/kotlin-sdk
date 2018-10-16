package org.tokend.sdk.api.assets

import org.tokend.sdk.api.assets.model.AssetChartData
import org.tokend.sdk.api.assets.model.AssetPair
import org.tokend.sdk.api.assets.model.NoPairFoundException
import org.tokend.sdk.api.assets.model.SimpleAsset
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.utils.BigDecimalUtil
import retrofit2.HttpException
import java.math.BigDecimal
import java.net.HttpURLConnection

open class AssetsApi(
        protected val assetsService: AssetsService
) {
    /**
     * Will return all existing assets in the system.
     * See <a href="https://tokend.gitlab.io/docs/?http#assets">Docs</a>
     */
    open fun get(): ApiRequest<List<SimpleAsset>> {
        return SimpleRetrofitApiRequest(
                assetsService.getAssets()
        )
    }

    /**
     * Will return specific asset by it's code.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-asset-by-code">Docs</a>
     */
    open fun getByCode(assetCode: String): ApiRequest<SimpleAsset> {
        return SimpleRetrofitApiRequest(
                assetsService.getAsset(assetCode)
        )
    }

    /**
     * Will return all existing asset pairs in the system.
     * See <a href="https://tokend.gitlab.io/docs/?http#get-asset-pairs-list">Docs</a>
     */
    open fun getPairs(): ApiRequest<List<AssetPair>> {
        return SimpleRetrofitApiRequest(
                assetsService.getAssetPairs()
        )
    }

    open fun convert(sourceAsset: String,
                     destAsset: String,
                     amount: BigDecimal): ApiRequest<BigDecimal> {
        return MappedRetrofitApiRequest(
                assetsService.convertAmount(
                        sourceAsset,
                        destAsset,
                        BigDecimalUtil.toPlainString(amount)
                ),
                { it.amount },
                { error ->
                    if (error is HttpException
                            && error.code() == HttpURLConnection.HTTP_NOT_FOUND)
                        NoPairFoundException()
                    else
                        error
                }
        )
    }

    open fun getChart(assetCode: String): ApiRequest<AssetChartData> {
        return SimpleRetrofitApiRequest(
                assetsService.getChart(assetCode)
        )
    }

    open fun getChart(baseAsset: String,
                      quoteAsset: String): ApiRequest<AssetChartData> {
        return getChart("$baseAsset-$quoteAsset")
    }
}