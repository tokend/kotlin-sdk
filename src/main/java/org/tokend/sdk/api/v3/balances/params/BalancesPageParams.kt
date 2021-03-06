package org.tokend.sdk.api.v3.balances.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see BalanceParams.Includes
 */
open class BalancesPageParams(
        val asset: String? = null,
        val account: String? = null,
        val assetOwner: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { putFilter("asset", it) }
            account?.also { putFilter("owner", it) }
            assetOwner?.also { putFilter("asset_owner", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var asset: String? = null
        private var account: String? = null
        private var assetOwner: String? = null

        fun withAsset(asset: String) = also { this.asset = asset }

        fun withAccount(account: String) = also { this.account = account }

        fun withAssetOwner(assetOwner: String) = also { this.assetOwner = assetOwner }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): BalancesPageParams {
            return BalancesPageParams(asset, account, assetOwner, include, pagingParams)
        }
    }
}