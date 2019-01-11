package org.tokend.sdk.api.v2.assetpairs.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.JsonApiQueryParams
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
        val pagingParams: PagingParamsV2? = null
) : AssetPairParams(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            policies?.also {
                put("policy", policies.map { it.value.toLong() }.bitmask())
            }
            baseAsset?.also { put("base_asset", it) }
            quoteAsset?.also { put("quote_asset", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }

    class Builder : JsonApiQueryParams.Builder() {
        private var policies: Collection<AssetPairPolicy>? = null
        private var baseAsset: String? = null
        private var quoteAsset: String? = null
        private var pagingParams: PagingParamsV2? = null

        fun withPolicies(policies: Collection<AssetPairPolicy>) = also { this.policies = policies }

        fun withPolicies(vararg policies: AssetPairPolicy) = also { this.policies = policies.toList() }

        fun withBaseAsset(asset: String) = also { this.baseAsset = asset }

        fun withQuoteAsset(asset: String) = also { this.quoteAsset = asset }

        fun withPagingParams(pagingParams: PagingParamsV2) = also { this.pagingParams = pagingParams }

        override fun build(): JsonApiQueryParams {
            return AssetPairsPageParams(policies, baseAsset, quoteAsset, include, pagingParams)
        }
    }
}