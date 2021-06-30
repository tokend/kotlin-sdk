package org.tokend.sdk.api.v3.orderbook.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

open class MatchesPageParams(
        val baseAsset: String? = null,
        val quoteAsset: String? = null,
        val orderBookId: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            putFilter("base_asset", baseAsset)
            putFilter("quote_asset", quoteAsset)
            putFilter("order_book", orderBookId)
        }
    }

    open class Builder: PageQueryParams.Builder() {
        protected open var baseAsset: kotlin.String? = null
        protected open var quoteAsset: kotlin.String? = null
        protected open var orderBookId: kotlin.String? = null

        open fun withBaseAsset(baseAsset: kotlin.String) = also { this.baseAsset = baseAsset }

        open fun withQuoteAsset(quoteAsset: kotlin.String) = also { this.quoteAsset = quoteAsset }

        open fun withOrderBookId(orderBookId: kotlin.String) = also { this.orderBookId = orderBookId }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): MatchesPageParams =
                MatchesPageParams(baseAsset, quoteAsset, orderBookId, pagingParams, include)
    }
}