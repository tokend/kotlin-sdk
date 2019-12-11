package org.tokend.sdk.api.ingester.assets

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.AssetResource
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

open class IngesterAssetsApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<AssetResource> {
        return requests.get(
                url = "horizon/assets/$id",
                responseClass = AssetResource::class.java,
                queryMap = params.map()
        )
    }
}