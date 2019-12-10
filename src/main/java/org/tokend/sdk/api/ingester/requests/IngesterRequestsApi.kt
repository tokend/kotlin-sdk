package org.tokend.sdk.api.ingester.requests

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.ReviewableRequestResource
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IngesterRequestsApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<ReviewableRequestResource>> {
        return requests.getPage(
                url = "requests",
                pageItemClass = ReviewableRequestResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<ReviewableRequestResource> {
        return requests.get(
                url = "requests/$id",
                responseClass = ReviewableRequestResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getAccountRequestById(accountId: String,
                                   requestId: String,
                                   params: JsonApiQueryParams? = null): ApiRequest<ReviewableRequestResource> {
        return requests.get(
                url = "accounts/$accountId/requests/$requestId",
                responseClass = ReviewableRequestResource::class.java,
                queryMap = params.map()
        )
    }
}