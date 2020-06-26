package org.tokend.sdk.api.integrations.mergedhistory

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataPageWithNamedCursors
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.custom.CustomRequestsApi
import org.tokend.sdk.api.integrations.mergedhistory.model.generated.resources.EventResource
import org.tokend.sdk.api.integrations.mergedhistory.params.MergedHistoryPageParams

open class MergedHistoryApi(
        protected open val customRequestsApi: CustomRequestsApi
) {
    @JvmOverloads
    open fun getPage(params: MergedHistoryPageParams? = null
    ): ApiRequest<DataPageWithNamedCursors<EventResource>> {
        return customRequestsApi.getPageWithNamedCursors(
                url = "integrations/history",
                queryMap = params.map(),
                pageItemClass = EventResource::class.java
        )
    }
}