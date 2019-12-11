package org.tokend.sdk.api.ingester.history

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.ingester.generated.resources.OperationResource
import org.tokend.sdk.api.ingester.generated.resources.ParticipantsEffectResource
import org.tokend.sdk.api.ingester.history.params.IngesterHistoryPageParams
import org.tokend.sdk.api.ingester.history.params.IngesterOperationsPageParams

open class IngesterHistoryApi(
        protected val requests: CustomRequestsApi
) {
    @JvmOverloads
    fun getPage(params: IngesterHistoryPageParams? = null)
            : ApiRequest<DataPage<ParticipantsEffectResource>> {
        return requests.getPage(
                url = "horizon/history",
                pageItemClass = ParticipantsEffectResource::class.java,
                queryMap = params.map()
        )
    }

    @JvmOverloads
    fun getOperationsPage(params: IngesterOperationsPageParams? = null)
            : ApiRequest<DataPage<OperationResource>> {
        return requests.getPage(
                url = "horizon/operations",
                pageItemClass = OperationResource::class.java,
                queryMap = params.map()
        )
    }
}