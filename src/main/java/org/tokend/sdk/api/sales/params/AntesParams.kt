package org.tokend.sdk.api.sales.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.api.base.params.PagingParams

open class AntesParams(
        val pagingParams: PagingParams? = null,
        val participantBalanceId: String? = null,
        val saleId: Long? = null
) : QueryParams, PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    pagingParams?.also { putAll(it.map()) }
                    participantBalanceId?.also { put("participant_balance_id", it) }
                    saleId?.also { put("sale_id", it) }
                }
    }
}