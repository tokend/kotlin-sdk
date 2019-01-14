package org.tokend.sdk.api.v2.accounts.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams
import org.tokend.sdk.utils.extentions.bitmask
import org.tokend.wallet.xdr.AccountType
import org.tokend.wallet.xdr.SignerType

/**
 * @see AccountParamsV2.Includes
 */
open class AccountsPageParamsV2(
        val accountTypes: Collection<AccountType>? = null,
        val signerTypes: Collection<SignerType>? = null,
        val isBlocked: Boolean? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            accountTypes?.also {
                put("account_type", accountTypes.map { it.value.toLong() }.bitmask())
            }
            signerTypes?.also {
                put("signer_type", signerTypes.map { it.value.toLong() }.bitmask())
            }
            isBlocked?.also { put("is_blocked", isBlocked) }
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

        override fun build(): AccountsPageParamsV2 {
            return AccountsPageParamsV2(accountTypes, signerTypes, isBlocked, include, pagingParams)
        }
    }
}