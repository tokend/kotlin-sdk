package org.tokend.sdk.api.base.model.operations

import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import java.math.BigDecimal
import java.util.*

/**
 * Represents base transfer operation with no type.
 */
open class BaseTransferOperation(
        override val id: String,
        override val pagingToken: String,
        override val date: Date,
        override val state: OperationState,
        override val type: OperationType,
        override val sourceAccount: String,
        override val amount: BigDecimal,
        override val asset: String,
        override val isReceived: Boolean
) : TransferOperation {
    companion object {
        fun fromPaymentRecord(record: UnifiedOperationRecord, contextAccountId: String): BaseTransferOperation {
            return BaseTransferOperation(
                    id = record.id,
                    pagingToken = record.pagingToken,
                    asset = record.asset ?: "",
                    amount = record.amount ?: BigDecimal.ZERO,
                    sourceAccount = record.sourceAccount ?: "",
                    state = OperationState.fromLiteral(record.state),
                    type = OperationType.UNKNOWN,
                    date = record.ledgerCloseTime,
                    isReceived = contextAccountId != record.sourceAccount
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is TransferOperation
                && other.id == this.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}