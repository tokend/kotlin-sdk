package org.tokend.sdk.api.v2.history.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams

/**
 * @see ParticipantEffectsParams.Includes
 */
class ParticipantEffectsPageParams(
        val account: String? = null,
        val balance: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            account?.also { put("account", it) }
            balance?.also { put("balance", it) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var account: String? = null
        private var balance: String? = null

        fun withAccount(account: String) = also { this.account = account }

        fun withBalance(balance: String) = also { this.balance = balance }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): ParticipantEffectsPageParams {
            return ParticipantEffectsPageParams(account, balance, include, pagingParams)
        }
    }
}