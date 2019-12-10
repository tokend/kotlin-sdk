package org.tokend.sdk.api.ingester.history

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.BaseResource
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.OperationResource
import org.tokend.sdk.api.ingester.generated.resources.ParticipantsEffectResource

open class IngesterHistoryApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    fun getPage(params: PagingParams? = null): ApiRequest<DataPage<ParticipantsEffectResource>> {
        return requests.getPage(
                url = "history",
                pageItemClass = ParticipantsEffectResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    fun getOperationsPage(params: PagingParams? = null): ApiRequest<DataPage<OperationResource>> {
        return requests.getPage(
                url = "operations",
                pageItemClass = OperationResource::class.java,
                queryMap = params.map()
        )
    }
}