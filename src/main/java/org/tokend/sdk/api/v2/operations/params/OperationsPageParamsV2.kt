package org.tokend.sdk.api.v2.operations.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v2.base.PageQueryParams
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
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include), PagingParamsHolder {
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
        }
    }

    class Subsets {
        companion object {
            const val ALL = "all"
            const val PAYMENTS = "payments"
        }
    }

    class Builder : PageQueryParams.Builder() {
        private var transaction: String? = null
        private var account: String? = null
        private var accountType: AccountType? = null
        private var reference: String? = null
        private var states: Collection<Int>? = null
        private var subset: String? = null

        fun withTransaction(transaction: String) = also { this.transaction = transaction }

        fun withAccount(account: String) = also { this.account = account }

        fun withAccountType(accountType: AccountType) = also { this.accountType = accountType }

        fun withReference(reference: String) = also { this.reference = reference }

        fun withStates(states: Collection<Int>) = also { this.states = states }

        fun withStates(vararg states: Int) = also { this.states = states.toList() }

        fun withSubset(subset: String) = also { this.subset = subset }

        override fun build(): OperationsPageParamsV2 {
            return OperationsPageParamsV2(transaction, account, accountType, reference, states, subset, include, pagingParams)
        }
    }
}