package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil
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
        override val asset: String
) : Transaction {
    override fun isSent(account: String): Boolean {
        return sourceAccount == account
    }

    companion object {
        fun fromPaymentRecord(record: PaymentRecord): BaseTransaction {
            return BaseTransaction(
                    id = record.id,
                    pagingToken = record.pagingToken,
                    asset = record.asset ?: "",
                    amount = BigDecimalUtil.valueOf(record.amount),
                    sourceAccount = record.sourceAccount ?: "",
                    state = TransactionState.fromCode(record.state),
                    type = TransactionType.UNKNOWN,
                    date = record.ledgerCloseTime
            )
        }
    }
}