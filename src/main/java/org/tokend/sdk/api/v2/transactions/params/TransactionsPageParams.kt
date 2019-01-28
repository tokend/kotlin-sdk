package org.tokend.sdk.api.v2.transactions.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams

class TransactionsPageParams(
        val account: String? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            account?.also { put("account_id", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var account: String? = null

        fun withAccount(account: String) = also { this.account = account }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): TransactionsPageParams {
            return TransactionsPageParams(account, include, pagingParams)
        }
    }
}