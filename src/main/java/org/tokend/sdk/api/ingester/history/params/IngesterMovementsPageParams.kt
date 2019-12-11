package org.tokend.sdk.api.ingester.history.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.ingester.history.params.IngesterMovementsPageParams.Includes
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see Includes
 */
open class IngesterMovementsPageParams(
        val account: String? = null,
        val balance: String? = null,
        val asset: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any>  = super.map().toMutableMap().apply {
        account?.also { putFilter("account", it) }
        balance?.also { putFilter("balance", it) }
        asset?.also { putFilter("asset", it) }
    }

    open class Builder: PageQueryParams.Builder() {
        protected open var account: kotlin.String? = null
        protected open var balance: kotlin.String? = null
        protected open var asset: kotlin.String? = null

        open fun withAccount(account: kotlin.String) = also { this.account = account }

        open fun withBalance(balance: kotlin.String) = also { this.balance = balance }

        open fun withAsset(asset: kotlin.String) = also { this.asset = asset }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterMovementsPageParams =
                IngesterMovementsPageParams(account, balance, asset, pagingParams, include)
    }

    companion object Includes {
        const val OPERATION = "operation"
        const val OPERATION_DETAILS = "operation.details"
        const val EFFECT = "effect"
    }
}