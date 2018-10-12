package org.tokend.sdk.api.accounts.params

import org.tokend.sdk.api.base.params.OperationsParams
import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.QueryParams

class PaymentsParams(
        val pagingParams: PagingParams? = null,
        val operationsParams: OperationsParams? = null,
        val asset: String? = null
): QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            pagingParams?.also { putAll(it.map()) }
            operationsParams?.also { putAll(it.map()) }
            asset?.also { put("asset", it) }
        }
    }
}