package org.tokend.sdk.api.v2.requests

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.ReviewableRequestResource
import org.tokend.sdk.api.v2.requests.params.RequestParamsV2
import org.tokend.sdk.api.v2.requests.params.RequestsPageParamsV2

open class RequestsApiV2(
        protected open val requestsService: RequestsServiceV2
) {
    /**
     * @return requests list page
     */
    open fun get(params: RequestsPageParamsV2? = null): ApiRequest<DataPage<ReviewableRequestResource>> {
        return MappedRetrofitApiRequest(
                requestsService.getRequests(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return request by it's ID
     */
    open fun getById(requestId: String,
                     params:RequestParamsV2? = null): ApiRequest<ReviewableRequestResource> {
        return MappedRetrofitApiRequest(
                requestsService.getRequestById(
                        requestId,
                        params.map()
                ),
                JSONAPIDocument<ReviewableRequestResource>::get
        )
    }
}