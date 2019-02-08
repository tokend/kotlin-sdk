package org.tokend.sdk.api.v2.assetpairs.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams
import org.tokend.sdk.utils.extentions.bitmask
import org.tokend.wallet.xdr.AssetPairPolicy

/**
 * @see AssetPairParams.Includes
 */
open class AssetPairsPageParams(
        val policies: Collection<AssetPairPolicy>? = null,
        val baseAsset: String? = null,
        val quoteAsset: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            policies?.also {
                putFilter("policy", policies.map { it.value.toLong() }.bitmask())
            }
            baseAsset?.also { putFilter("base_asset", it) }
            quoteAsset?.also { putFilter("quote_asset", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var policies: Collection<AssetPairPolicy>? = null
        private var baseAsset: String? = null
        private var quoteAsset: String? = null

        fun withPolicies(policies: Collection<AssetPairPolicy>) = also { this.policies = policies }

        fun withPolicies(vararg policies: AssetPairPolicy) = also { this.policies = policies.toList() }

        fun withBaseAsset(asset: String) = also { this.baseAsset = asset }

        fun withQuoteAsset(asset: String) = also { this.quoteAsset = asset }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): AssetPairsPageParams {
            return AssetPairsPageParams(policies, baseAsset, quoteAsset, include, pagingParams)
        }
    }
}