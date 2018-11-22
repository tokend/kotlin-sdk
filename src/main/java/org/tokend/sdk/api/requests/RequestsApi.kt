package org.tokend.sdk.api.requests

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.requests.model.SimpleReviewableRequest
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
}