package org.tokend.sdk.api.v3.accounts.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams
import org.tokend.sdk.utils.extentions.bitmask
import org.tokend.wallet.xdr.AccountType
import org.tokend.wallet.xdr.SignerType

/**
 * @see AccountParamsV3.Includes
 */
open class AccountsPageParamsV3(
        val accountTypes: Collection<AccountType>? = null,
        val signerTypes: Collection<SignerType>? = null,
        val isBlocked: Boolean? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            accountTypes?.also {
                putFilter("account_type", accountTypes.map { it.value.toLong() }.bitmask())
            }
            signerTypes?.also {
                putFilter("signer_type", signerTypes.map { it.value.toLong() }.bitmask())
            }
            isBlocked?.also { putFilter("is_blocked", isBlocked) }
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var accountTypes: Collection<AccountType>? = null
        private var signerTypes: Collection<SignerType>? = null
        private var isBlocked: Boolean? = null

        fun withAccountTypes(accountTypes: Collection<AccountType>?) = also { this.accountTypes = accountTypes }

        fun withAccountTypes(vararg accountTypes: AccountType) = also { this.accountTypes = accountTypes.toList() }

        fun withSignerTypes(signerTypes: Collection<SignerType>?) = also { this.signerTypes = signerTypes }

        fun withSignerTypes(vararg signerTypes: SignerType) = also { this.signerTypes = signerTypes.asList() }

        fun withIsBlocked(isBlocked: Boolean?) = also { this.isBlocked = isBlocked }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): AccountsPageParamsV3 {
            return AccountsPageParamsV3(accountTypes, signerTypes, isBlocked, include, pagingParams)
        }
    }
}