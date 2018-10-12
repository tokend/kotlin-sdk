package org.tokend.sdk.api.sales.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.QueryParams

open class AntesParams(
        val pagingParams: PagingParams? = null,
        val participantBalanceId: String? = null,
        val saleId: Long? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    pagingParams?.also { putAll(it.map()) }
                    participantBalanceId?.also { put("participant_balance_id", it) }
                    saleId?.also { put("sale_id", it) }
                }
    }
}