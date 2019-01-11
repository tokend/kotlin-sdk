package org.tokend.sdk.api.v2.sales.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.JsonApiQueryParams

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
        val pagingParams: PagingParamsV2? = null
) : SaleParamsV2(includes), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

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
            pagingParams?.also { putAll(it.map()) }
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

    class Builder : JsonApiQueryParams.Builder() {
        private var name: String? = null
        private var baseAsset: String? = null
        private var ownerAccount: String? = null
        private var openOnly: Boolean? = null
        private var upcoming: Boolean? = null
        private var voting: Boolean? = null
        private var promotions: Boolean? = null
        private var sortBy: Int? = null
        private var pagingParams: PagingParamsV2? = null

        fun withName(name: String) = also { this.name = name }

        fun withBaseAsset(asset: String) = also { this.baseAsset = asset }

        fun withOwnerAccount(account: String) = also { this.ownerAccount = account }

        fun withOpenOnly(openOnly: Boolean) = also { this.openOnly = openOnly }

        fun withUpcoming(upcoming: Boolean) = also { this.upcoming = upcoming }

        fun withVoting(voting: Boolean) = also { this.voting = voting }

        fun withPromotions(promotions: Boolean) = also { this.promotions = promotions }

        fun withSortBy(sortBy: Int) = also { this.sortBy = sortBy }

        fun withPagingParams(pagingParams: PagingParamsV2) = also { this.pagingParams = pagingParams }

        override fun build(): JsonApiQueryParams {
            return SalesPageParamsV2(name, baseAsset, ownerAccount, openOnly, upcoming, voting, promotions, sortBy, include, pagingParams)
        }
    }
}