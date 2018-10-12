package org.tokend.sdk.api.trades.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.QueryParams

class OrderBookParams(
        val baseAsset: String,
        val quoteAsset: String,
        val pagingParams: PagingParams? = null,
        val isBuy: Boolean? = null,
        val orderBookId: Long? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    put("base_asset", baseAsset)
                    put("quote_asset", quoteAsset)
                    pagingParams?.also { putAll(it.map()) }
                    isBuy?.also { put("is_buy", it) }
                    orderBookId?.also { put("order_book_id", it) }
                }
    }
}