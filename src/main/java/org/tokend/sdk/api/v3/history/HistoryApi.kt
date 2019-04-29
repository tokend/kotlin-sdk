package org.tokend.sdk.api.v3.history

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.generated.resources.ParticipantEffectResource
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsPageParams

open class HistoryApi(
        protected open val historyService: HistoryService
) {
    /**
     * @return participants effects page
     */
    open fun get(params: ParticipantEffectsPageParams? = null): ApiRequest<DataPage<ParticipantEffectResource>> {
        return MappedRetrofitApiRequest(
                historyService.getHistory(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return balance-related participants effects page
     */
    open fun getMovements(params: ParticipantEffectsPageParams? = null): ApiRequest<DataPage<ParticipantEffectResource>> {
        return MappedRetrofitApiRequest(
                historyService.getMovements(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}