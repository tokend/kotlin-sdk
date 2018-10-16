package org.tokend.sdk.api.accounts.params

import org.tokend.sdk.api.base.params.OperationsParams
import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.api.base.params.PagingParams

class PaymentsParams(
        val pagingParams: PagingParams? = null,
        val operationsParams: OperationsParams? = null,
        val asset: String? = null
) : QueryParams, PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            pagingParams?.also { putAll(it.map()) }
            operationsParams?.also { putAll(it.map()) }
            asset?.also { put("asset", it) }
        }
    }
}