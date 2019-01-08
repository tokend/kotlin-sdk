package org.tokend.sdk.api.v2.sales.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2

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
        include: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : SaleParamsV2(include), PagingParamsHolder {
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
}