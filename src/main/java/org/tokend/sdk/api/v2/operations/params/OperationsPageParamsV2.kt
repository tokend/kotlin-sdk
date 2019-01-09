package org.tokend.sdk.api.v2.operations.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.utils.extentions.bitmask
import org.tokend.wallet.xdr.AccountType

/**
 * @see OperationParamsV2.Includes
 * @see OperationsPageParamsV2.Subsets
 */
open class OperationsPageParamsV2(
        val transaction: String? = null,
        val account: String? = null,
        val accountType: AccountType? = null,
        val reference: String? = null,
        val states: Collection<Int>? = null,
        val subset: String? = null,
        include: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : OperationParamsV2(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            transaction?.also { put("tx_id", it) }
            account?.also { put("account_id", it) }
            reference?.also { put("reference", it) }
            accountType?.also { put("account_type", it.value) }
            states?.also { put("states", states.map { it.toLong() }.bitmask()) }
            subset?.also { put("subset", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }

    class Subsets {
        companion object {
            const val ALL = "all"
            const val PAYMENTS = "payments"
        }
    }
}