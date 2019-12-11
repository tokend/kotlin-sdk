package org.tokend.sdk.api.ingester.requests

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.ReviewableRequestResource
import org.tokend.sdk.api.ingester.requests.params.IngesterRequestParams
import org.tokend.sdk.api.ingester.requests.params.IngesterRequestsPageParams

open class IngesterRequestsApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: IngesterRequestsPageParams? = null): ApiRequest<DataPage<ReviewableRequestResource>> {
        return requests.getPage(
                url = "horizon/requests",
                pageItemClass = ReviewableRequestResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: IngesterRequestParams? = null): ApiRequest<ReviewableRequestResource> {
        return requests.get(
                url = "horizon/requests/$id",
                responseClass = ReviewableRequestResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getAccountRequestById(accountId: String,
                                   requestId: String,
                                   params: IngesterRequestParams? = null): ApiRequest<ReviewableRequestResource> {
        return requests.get(
                url = "horizon/accounts/$accountId/requests/$requestId",
                responseClass = ReviewableRequestResource::class.java,
                queryMap = params.map()
        )
    }
}