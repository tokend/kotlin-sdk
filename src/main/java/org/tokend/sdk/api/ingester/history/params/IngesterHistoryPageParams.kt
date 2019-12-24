package org.tokend.sdk.api.ingester.history.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.ingester.history.model.EffectModifier
import org.tokend.sdk.api.ingester.history.model.EffectType
import org.tokend.sdk.api.ingester.history.params.IngesterHistoryPageParams.Includes
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see Includes
 */
open class IngesterHistoryPageParams(
        open val account: String? = null,
        open val effectType: EffectType? = null,
        open val effectModifier: EffectModifier? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> = super.map().toMutableMap().apply {
        account?.also { putFilter("account", it) }
        effectType?.also { putFilter("effect_type", it.i) }
        effectModifier?.also { putFilter("effect_modifier", it.i) }
    }

    open class Builder: PageQueryParams.Builder() {
        protected open var account: kotlin.String? = null
        protected open var effectType: org.tokend.sdk.api.ingester.history.model.EffectType? = null
        protected open var effectModifier: org.tokend.sdk.api.ingester.history.model.EffectModifier? = null

        open fun withAccount(account: kotlin.String) = also { this.account = account }

        open fun withEffectType(effectType: org.tokend.sdk.api.ingester.history.model.EffectType) = also { this.effectType = effectType }

        open fun withEffectModifier(effectModifier: org.tokend.sdk.api.ingester.history.model.EffectModifier) = also { this.effectModifier = effectModifier }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): IngesterHistoryPageParams =
                IngesterHistoryPageParams(account, effectType, effectModifier, pagingParams, include)
    }

    companion object Includes {
        const val OPERATION = "operation"
        const val OPERATION_DETAILS = "operation.details"
        const val EFFECT = "effect"
    }
}