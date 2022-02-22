package org.tokend.sdk.api.v3.accounts.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see AccountParamsV3.Includes
 */
open class AccountsPageParamsV3(
    val role: Long? = null,
    val account: String? = null,
    include: Collection<String>? = null,
    pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            putFilter("role", role)
            putFilter("account", account)
        }
    }

    open class Builder : PageQueryParams.Builder() {
        protected open var role: kotlin.Long? = null
        protected open var account: kotlin.String? = null

        open fun withRole(role: kotlin.Long) = also { this.role = role }

        open fun withAccount(account: kotlin.String) = also { this.account = account }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) =
            also { super.withPagingParams(pagingParams) }

        override fun build(): AccountsPageParamsV3 =
            AccountsPageParamsV3(role, account, include, pagingParams)
    }
}