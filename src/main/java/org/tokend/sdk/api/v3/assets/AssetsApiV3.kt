package org.tokend.sdk.api.v3.assets

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.AssetResource
import org.tokend.sdk.api.v3.assets.params.AssetParams
import org.tokend.sdk.api.v3.assets.params.AssetsPageParams

open class AssetsApiV3(
        protected open val assetsService: AssetsServiceV3
) {
    /**
     * @return assets list page
     */
    open fun get(params: AssetsPageParams? = null): ApiRequest<DataPage<AssetResource>> {
        return MappedRetrofitApiRequest(
                assetsService.getAssets(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return asset by it's ID
     */
    open fun getById(assetId: String,
                     params: AssetParams? = null): ApiRequest<AssetResource> {
        return MappedRetrofitApiRequest(
                assetsService.getAssetById(
                        assetId,
                        params.map()
                ),
                JSONAPIDocument<AssetResource>::get
        )
    }
}