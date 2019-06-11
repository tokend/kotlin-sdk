package org.tokend.sdk.api.v3.atomicswap.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see AtomicSwapAskParams.Includes
 */
open class AtomicSwapAsksPageParams(
        val baseAsset: String? = null,
        val owner: String? = null,
        val quoteAssets: Collection<String>? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            baseAsset?.also { putFilter("base_asset", it) }
            owner?.also { putFilter("owner", it) }
            quoteAssets?.also { putFilter("quote_assets", it.joinToString(",")) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var baseAsset: String? = null
        private var owner: String? = null
        private var quoteAssets: Collection<String>? = null

        fun withBaseAsset(baseAsset: String) = apply { this.baseAsset = baseAsset }

        fun withOwner(owner: String) = apply { this.owner = owner }

        fun withQuoteAssets(quoteAssets: Collection<String>) = apply { this.quoteAssets = quoteAssets }

        fun withQuoteAssets(vararg quoteAssets: String) = also {
            withQuoteAssets(quoteAssets.asList())
        }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): AtomicSwapAsksPageParams {
            return AtomicSwapAsksPageParams(baseAsset, owner, quoteAssets, include, pagingParams)
        }
    }
}