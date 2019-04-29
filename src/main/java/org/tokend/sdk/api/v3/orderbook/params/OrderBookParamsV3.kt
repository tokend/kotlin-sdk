package org.tokend.sdk.api.v3.orderbook.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see OrderBookParamsV3.Includes
 */
class OrderBookParamsV3(
        val maxEntries: Int? = null,
        include: Collection<String>?
) : JsonApiQueryParams(include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            maxEntries?.also { put("max_entries", it) }
        }
    }

    class Builder : JsonApiQueryParams.Builder() {
        private var maxEntries: Int? = null

        fun withMaxEntries(maxEntries: Int) = also { this.maxEntries = maxEntries }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): OrderBookParamsV3 {
            return OrderBookParamsV3(maxEntries, include)
        }
    }

    class Includes {
        companion object {
            const val BASE_ASSET = "base_asset"
            const val QUOTE_ASSET = "quote_asset"
            const val BUY_ENTRIES = "buy_entries"
            const val SELL_ENTRIES = "sell_entries"
        }
    }
}