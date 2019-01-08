package org.tokend.sdk.api.v2.transactions.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.JsonApiQueryParams

class TransactionsPageParams(
        val account: String? = null,
        include: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : JsonApiQueryParams(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            account?.also { put("account_id", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }
}