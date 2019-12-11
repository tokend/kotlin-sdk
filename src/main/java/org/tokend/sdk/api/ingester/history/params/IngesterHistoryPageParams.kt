package org.tokend.sdk.api.ingester.history.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.ingester.history.params.IngesterHistoryPageParams.Includes
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see Includes
 */
open class IngesterHistoryPageParams(
        open val account: String? = null,
        open val effectType: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        account?.also { putFilter("account", it) }
        effectType?.also { putFilter("effect_type", it) }
    }

    open class Builder : PageQueryParams.Builder() {
        protected open var account: kotlin.String? = null
        protected open var effectType: kotlin.String? = null

        open fun withAccount(account: kotlin.String) = also { this.account = account }

        open fun withEffectType(effectType: kotlin.String) = also { this.effectType = effectType }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterHistoryPageParams =
                IngesterHistoryPageParams(account, effectType, pagingParams, include)
    }

    companion object Includes {
        const val OPERATION = "operation"
        const val OPERATION_DETAILS = "operation.details"
        const val EFFECT = "effect"
    }
}