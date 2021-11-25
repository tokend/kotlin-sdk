package org.tokend.sdk.api.v3.history

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.v3.model.generated.resources.OperationResource
import org.tokend.sdk.api.v3.model.generated.resources.ParticipantsEffectResource
import org.tokend.sdk.api.v3.history.params.OperationsPageParams
import org.tokend.sdk.api.v3.history.params.ParticipantEffectsPageParams

open class HistoryApi(
        protected open val historyService: HistoryService
) {
    /**
     * @return participants effects page
     */
    @JvmOverloads
    open fun get(params: ParticipantEffectsPageParams? = null): ApiRequest<DataPage<ParticipantsEffectResource>> {
        return MappedRetrofitApiRequest(
                historyService.getHistory(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    /**
     * @return balance-related participants effects page
     */
    @JvmOverloads
    open fun getMovements(params: ParticipantEffectsPageParams? = null): ApiRequest<DataPage<ParticipantsEffectResource>> {
        return MappedRetrofitApiRequest(
                historyService.getMovements(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }

    @JvmOverloads
    open fun getOperations(params: OperationsPageParams? = null): ApiRequest<DataPage<OperationResource>> {
        return MappedRetrofitApiRequest(
                historyService.getOperations(params.map()),
                DataPage.Companion::fromPageDocument
        )
    }
}