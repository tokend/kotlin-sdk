package org.tokend.sdk.api.v2.balances.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.JsonApiQueryParams

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

    class Builder : JsonApiQueryParams.Builder() {
        private var asset: String? = null
        private var account: String? = null
        private var pagingParams: PagingParamsV2? = null

        fun withAsset(asset: String) = also { this.asset = asset }

        fun withAccount(account: String) = also { this.account = account }

        fun withPagingParams(pagingParams: PagingParamsV2) = also { this.pagingParams = pagingParams }

        override fun build(): JsonApiQueryParams {
            return BalancesPageParams(asset, account, include, pagingParams)
        }
    }
}