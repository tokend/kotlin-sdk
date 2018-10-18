package org.tokend.sdk.api.base.model.operations

import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import java.math.BigDecimal

/**
 * Represents operation of transferring some amount of some asset
 * between two accounts.
 */
open class PaymentOperation(
        private val base: BaseTransferOperation,
        override val sourceAccount: String,
        val destAccount: String,
        val senderFee: ComplexFee,
        val recipientFee: ComplexFee,
        val feePaidBySender: Boolean,
        val subject: String?
) : TransferOperation by base {
    override val type = OperationType.PAYMENT

    var counterpartyNickname: String? = null

    companion object {
        fun fromUnifiedOperationRecord(record: UnifiedOperationRecord, contextAccountId: String): PaymentOperation {
            val baseTransaction = BaseTransferOperation.fromPaymentRecord(record, contextAccountId)

            val senderFee =
                    if (record.sourceFeeData != null)
                        ComplexFee(
                                fixed = record.sourceFeeData.fixedFee,
                                percent = record.sourceFeeData.paymentFee,
                                asset = record.sourceFeeData.asset
                        )
                    else
                        ComplexFee(
                                fixed = record.sourceFixedFee ?: BigDecimal.ZERO,
                                percent = record.sourcePaymentFee ?: BigDecimal.ZERO,
                                asset = baseTransaction.asset
                        )

            val recipientFee =
                    if (record.destFeeData != null)
                        ComplexFee(
                                fixed = record.destFeeData.fixedFee,
                                percent = record.destFeeData.paymentFee,
                                asset = record.destFeeData.asset
                        )
                    else
                        ComplexFee(
                                fixed = record.destFixedFee ?: BigDecimal.ZERO,
                                percent = record.destPaymentFee ?: BigDecimal.ZERO,
                                asset = baseTransaction.asset
                        )

            return PaymentOperation(
                    base = baseTransaction,
                    sourceAccount = record.sourceAccount ?: record.from ?: "",
                    destAccount = record.to ?: "",
                    senderFee = senderFee,
                    recipientFee = recipientFee,
                    feePaidBySender = record.isFeeFromSource,
                    subject = record.subject
            )
        }
    }
}