package org.tokend.sdk.api.v2.assetpairs.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
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
}