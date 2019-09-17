package org.tokend.sdk.api.integrations.marketplace.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see MarketplaceOfferParams.Includes
 */
class MarketplaceOffersPageParams(
        val owner: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            owner?.also { putFilter("owner", it) }
        }
    }

    class Builder: PageQueryParams.Builder() {
        private var owner: String? = null

        fun withOwner(owner: String) = also { this.owner = owner }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): MarketplaceOffersPageParams {
            return MarketplaceOffersPageParams(owner, pagingParams, include)
        }
    }
}