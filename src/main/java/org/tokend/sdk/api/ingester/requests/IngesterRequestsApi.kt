package org.tokend.sdk.api.ingester.requests

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.api.v3.base.PageQueryParams

open class IngesterRequestsApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: PageQueryParams? = null): ApiRequest<DataPage<BaseResource>> {
        return requests.getPage(
                url = "requests",
                pageItemClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getById(id: String,
                     params: JsonApiQueryParams? = null): ApiRequest<BaseResource> {
        return requests.get(
                url = "requests/$id",
                responseClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    open fun getAccountRequestById(accountId: String,
                                   requestId: String,
                                   params: JsonApiQueryParams? = null): ApiRequest<BaseResource> {
        return requests.get(
                url = "accounts/$accountId/requests/$requestId",
                responseClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }
}