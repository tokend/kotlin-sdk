package org.tokend.sdk.api.trades.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.QueryParams

class OrdersParams(
        val baseAsset: String,
        val quoteAsset: String,
        val pagingParams: PagingParams? = null,
        val orderBookId: Long? = null
) : QueryParams, PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    put("base_asset", baseAsset)
                    put("quote_asset", quoteAsset)
                    pagingParams?.also { putAll(it.map()) }
                    orderBookId?.also { put("order_book_id", it) }
                }    }
}