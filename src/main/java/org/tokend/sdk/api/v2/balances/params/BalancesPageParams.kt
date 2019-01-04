package org.tokend.sdk.api.v2.balances.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2

/**
 * @see BalanceParams.Includes
 */
open class BalancesPageParams(
        val asset: String? = null,
        val account: String? = null,
        include: Collection<String>?,
        val pagingParams: PagingParamsV2? = null
) : BalanceParams(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { put("asset", it) }
            account?.also { put("account", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }
}