package org.tokend.sdk.api.v2.sales.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams

/**
 * @see SaleParamsV2.Includes
 * @see SalesPageParamsV2.SortCriteria
 */
class SalesPageParamsV2(
        val name: String? = null,
        val baseAsset: String? = null,
        val ownerAccount: String? = null,
        val openOnly: Boolean? = null,
        val upcoming: Boolean? = null,
        val voting: Boolean? = null,
        val promotions: Boolean? = null,
        val sortBy: Int? = null,
        includes: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, includes) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            name?.also { put("name", it) }
            baseAsset?.also { put("base_asset", it) }
            ownerAccount?.also { put("owner", it) }
            openOnly?.also { put("open_only", it) }
            upcoming?.also { put("upcoming", it) }
            voting?.also { put("voting", it) }
            promotions?.also { put("promotions", it) }
            sortBy?.also { put("sort_by", it) }
        }
    }

    class SortCriteria {
        companion object {
            const val CREATED_AT = 1
            const val MOST_FUNDED = 2
            const val END_TIME = 3
            const val POPULARITY = 4
            const val START_TIME = 5
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var name: String? = null
        private var baseAsset: String? = null
        private var ownerAccount: String? = null
        private var openOnly: Boolean? = null
        private var upcoming: Boolean? = null
        private var voting: Boolean? = null
        private var promotions: Boolean? = null
        private var sortBy: Int? = null

        fun withName(name: String) = also { this.name = name }

        fun withBaseAsset(asset: String) = also { this.baseAsset = asset }

        fun withOwnerAccount(account: String) = also { this.ownerAccount = account }

        fun withOpenOnly(openOnly: Boolean) = also { this.openOnly = openOnly }

        fun withUpcoming(upcoming: Boolean) = also { this.upcoming = upcoming }

        fun withVoting(voting: Boolean) = also { this.voting = voting }

        fun withPromotions(promotions: Boolean) = also { this.promotions = promotions }

        fun withSortBy(sortBy: Int) = also { this.sortBy = sortBy }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): SalesPageParamsV2 {
            return SalesPageParamsV2(name, baseAsset, ownerAccount, openOnly, upcoming, voting, promotions, sortBy, include, pagingParams)
        }
    }
}