package org.tokend.sdk.api.ingester.history

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi

open class IngesterHistoryApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    fun getPage(params: PagingParams? = null): ApiRequest<DataPage<BaseResource>> {
        return requests.getPage(
                url = "history",
                pageItemClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    fun getOperationsPage(params: PagingParams? = null): ApiRequest<DataPage<BaseResource>> {
        return requests.getPage(
                url = "operations",
                pageItemClass = BaseResource::class.java,
                queryMap = params.map()
        )
    }
}