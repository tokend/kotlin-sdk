package org.tokend.sdk.api.v2.history

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v2.history.model.ParticipantEffectsResource
import org.tokend.sdk.api.v2.history.params.ParticipantEffectsPageParams

open class HistoryApi(
        protected open val historyService: HistoryService
) {
    /**
     * @return list of the effects page
     */
    open fun get(params: ParticipantEffectsPageParams? = null): ApiRequest<DataPage<ParticipantEffectsResource>> {
        return MappedRetrofitApiRequest(
                historyService.getHistory(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}