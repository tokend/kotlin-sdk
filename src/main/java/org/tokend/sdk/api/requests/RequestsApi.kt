package org.tokend.sdk.api.requests

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.requests.model.SimpleReviewableRequest
import org.tokend.sdk.api.requests.model.asset.SimpleAssetReviewableRequest
import org.tokend.sdk.api.requests.params.AssetRequestsParams
import org.tokend.sdk.api.requests.params.RequestsParams

open class RequestsApi(
        protected val requestsService: RequestsService
) {
    /**
     * Will return list of all reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-requests-history">Docs</a>
     */
    open fun getAll(params: RequestsParams?): ApiRequest<DataPage<SimpleReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getAll(
                        params?.map() ?: emptyMap()
                ),
                { DataPage.fromPage(it) }
        )
    }

    /**
     * Will return list of asset-related reviewable requests.
     * @see <a href="https://tokend.gitlab.io/docs/#get-assets-requests">Docs</a>
     */
    open fun getAssets(params: AssetRequestsParams?): ApiRequest<DataPage<SimpleAssetReviewableRequest>> {
        return MappedRetrofitApiRequest(
                requestsService.getAssets(
                        params?.map() ?: emptyMap()
                ),
                { DataPage.fromPage(it) }
        )
    }
}