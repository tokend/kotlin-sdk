package org.tokend.sdk.api.v2.offers.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams

/**
 * @see OfferParamsV2.Includes
 */
open class OffersPageParamsV2(
        val baseBalance: String? = null,
        val quoteBalance: String? = null,
        val baseAsset: String? = null,
        val quoteAsset: String? = null,
        val ownerAccount: String? = null,
        val orderBookId: Long? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            baseBalance?.also { put("base_balance", it) }
            quoteBalance?.also { put("quote_balance", it) }
            baseAsset?.also { put("base_asset", it) }
            quoteAsset?.also { put("quote_asset", it) }
            ownerAccount?.also { put("owner", it) }
            orderBookId?.also { put("order_book_id", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var baseBalance: String? = null
        private var quoteBalance: String? = null
        private var baseAsset: String? = null
        private var quoteAsset: String? = null
        private var ownerAccount: String? = null
        private var orderBookId: Long? = null

        fun withBaseBalance(balance: String) = also { this.baseBalance = balance }

        fun withQuoteBalance(balance: String) = also { this.quoteBalance = balance }

        fun withBaseAsset(asset: String) = also { this.baseAsset = asset }

        fun withQuoteAsset(asset: String) = also { this.quoteAsset = asset }

        fun withOwnerAccount(account: String) = also { this.ownerAccount = account }

        fun withOrderBookId(orderBookId: Long) = also { this.orderBookId = orderBookId }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): OffersPageParamsV2 {
            return OffersPageParamsV2(baseBalance, quoteBalance, baseAsset, quoteAsset, ownerAccount, orderBookId, include, pagingParams)
        }
    }
}