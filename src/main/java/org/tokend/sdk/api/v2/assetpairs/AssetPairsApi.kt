package org.tokend.sdk.api.v2.assetpairs

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v2.assetpairs.model.AssetPairResource
import org.tokend.sdk.api.v2.assetpairs.params.AssetPairParams
import org.tokend.sdk.api.v2.assetpairs.params.AssetPairsPageParams

open class AssetPairsApi(
        protected open val assetPairsService: AssetPairsService
) {
    /**
     * @return asset pairs list page
     */
    open fun get(params: AssetPairsPageParams? = null): ApiRequest<DataPage<AssetPairResource>> {
        return MappedRetrofitApiRequest(
                assetPairsService.getAssetPairs(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return asset pair by id
     */
    open fun getById(assetPairId: String,
                     params: AssetPairParams? = null): ApiRequest<AssetPairResource> {
        return MappedRetrofitApiRequest(
                assetPairsService.getAssetPairById(assetPairId, params.map()),
                JSONAPIDocument<AssetPairResource>::get
        )
    }
}