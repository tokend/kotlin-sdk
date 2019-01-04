package org.tokend.sdk.api.v2.accounts.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
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
        val pagingParams: PagingParamsV2? = null
) : AccountParamsV2(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            accountTypes?.also {
                put("account_type", accountTypes.map { it.value.toLong() }.bitmask())
            }
            signerTypes?.also {
                put("signer_type", signerTypes.map { it.value.toLong() }.bitmask())
            }
            isBlocked?.also { put("is_blocked", isBlocked) }
            pagingParams?.also { putAll(it.map()) }
        }
    }
}