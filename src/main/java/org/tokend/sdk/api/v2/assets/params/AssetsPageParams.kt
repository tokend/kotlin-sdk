package org.tokend.sdk.api.v2.assets.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2

/**
 * @see AssetParams.Includes
 */
open class AssetsPageParams(
        include: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : AssetParams(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            pagingParams?.also { putAll(it.map()) }
        }
    }
}