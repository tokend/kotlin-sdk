package org.tokend.sdk.api.ingester.assets

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.assets.params.IngesterAssetParams
import org.tokend.sdk.api.ingester.assets.params.IngesterAssetsPageParams
import org.tokend.sdk.api.ingester.generated.resources.AssetResource

open class IngesterAssetsApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getById(id: String,
                     params: IngesterAssetParams? = null)
            : ApiRequest<AssetResource> {
        return requests.get(
                url = "horizon/assets/$id",
                responseClass = AssetResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getPage(params: IngesterAssetsPageParams? = null)
            : ApiRequest<DataPage<AssetResource>> {
        return requests.getPage(
                url = "horizon/assets",
                pageItemClass = AssetResource::class.java,
                queryMap = params.map()
        )
    }
}