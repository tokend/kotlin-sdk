package org.tokend.sdk.api.v3.requests

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.ReviewableRequestResource
import org.tokend.sdk.api.v3.requests.params.AssetRequestPageParams
import org.tokend.sdk.api.v3.requests.params.ChangeRoleRequestPageParams
import org.tokend.sdk.api.v3.requests.params.RequestParamsV3
import org.tokend.sdk.api.v3.requests.params.RequestsPageParamsV3

open class RequestsApiV3(
        protected open val requestsService: RequestsServiceV3
) {
    /**
     * Will return requests list page
     */
    @JvmOverloads
    open fun get(params: RequestsPageParamsV3? = null): ApiRequest<DataPage<ReviewableRequestResource>> {
        return MappedRetrofitApiRequest(
                requestsService.getRequests(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * Will return request by it's ID
     */
    @JvmOverloads
    open fun getById(requestId: String,
                     params: RequestParamsV3? = null): ApiRequest<ReviewableRequestResource> {
        return MappedRetrofitApiRequest(
                requestsService.getRequestById(
                        requestId,
                        params.map()
                ),
                JSONAPIDocument<ReviewableRequestResource>::get
        )
    }

    /**
     * Will return change role requests list page
     */
    @JvmOverloads
    open fun getChangeRoleRequests(params: ChangeRoleRequestPageParams? = null)
            : ApiRequest<DataPage<ReviewableRequestResource>> {
        return MappedRetrofitApiRequest(
                requestsService.getChangeRoleRequests(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * Will return asset create requests list page
     */
    @JvmOverloads
    open fun getAssetCreateRequests(params: AssetRequestPageParams? = null)
            : ApiRequest<DataPage<ReviewableRequestResource>> {
        return MappedRetrofitApiRequest(
                requestsService.getAssetCreateRequests(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * Will return asset update requests list page
     */
    @JvmOverloads
    open fun getAssetUpdateRequests(params: AssetRequestPageParams? = null)
            : ApiRequest<DataPage<ReviewableRequestResource>> {
        return MappedRetrofitApiRequest(
                requestsService.getAssetUpdateRequests(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}