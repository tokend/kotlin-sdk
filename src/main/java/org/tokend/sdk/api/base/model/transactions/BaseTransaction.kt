package org.tokend.sdk.api.base.model.transactions

import org.tokend.sdk.api.accounts.model.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil
import org.tokend.sdk.utils.HashCodes
import java.math.BigDecimal
import java.util.*

open class BaseTransaction(
        override val id: String,
        override val pagingToken: String,
        override val date: Date,
        override val state: TransactionState,
        override val type: TransactionType,
        override val sourceAccount: String,
        override val amount: BigDecimal,
        override val asset: String,
        override val isReceived: Boolean
) : Transaction {
    companion object {
        fun fromPaymentRecord(record: PaymentRecord, contextAccountId: String): BaseTransaction {
            return BaseTransaction(
                    id = record.id,
                    pagingToken = record.pagingToken,
                    asset = record.asset ?: "",
                    amount = BigDecimalUtil.valueOf(record.amount),
                    sourceAccount = record.sourceAccount ?: "",
                    state = TransactionState.fromLiteral(record.state),
                    type = TransactionType.UNKNOWN,
                    date = record.ledgerCloseTime,
                    isReceived = contextAccountId != record.sourceAccount
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is Transaction
                && other.id == this.id
                && other.pagingToken == this.pagingToken
                && other.state == this.state
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(id, pagingToken, state)
    }
}