package org.tokend.sdk.api.v3.orderbook.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see OrderBookPageParams.Includes
 */
class OrderBookPageParams(
        val baseAsset: String? = null,
        val quoteAsset: String? = null,
        val isBuy: Boolean? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            baseAsset?.also { putFilter("base_asset", it) }
            quoteAsset?.also { putFilter("quote_asset", it) }
            isBuy?.also { putFilter("is_buy", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var baseAsset: String? = null
        private var quoteAsset: String? = null
        private var isBuy: Boolean? = null

        fun withBaseAsset(asset: String) = also { this.baseAsset = asset }

        fun withQuoteAsset(asset: String) = also { this.quoteAsset = asset }

        fun withIsBuy(isBuy: Boolean) = also { this.isBuy = isBuy }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): OrderBookPageParams {
            return OrderBookPageParams(baseAsset, quoteAsset, isBuy, include, pagingParams)
        }
    }

    class Includes {
        companion object {
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_asset"
        }
    }
}