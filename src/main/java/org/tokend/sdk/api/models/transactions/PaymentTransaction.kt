package org.tokend.sdk.api.models.transactions

import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil

class PaymentTransaction(
        private val base: BaseTransaction,
        override val sourceAccount: String,
        val destAccount: String,
        val senderFee: ComplexFee,
        val recipientFee: ComplexFee,
        val feePaidBySender: Boolean,
        val subject: String?
) : Transaction by base {
    override val type = TransactionType.PAYMENT

    var counterpartyNickname: String? = null

    companion object {
        fun fromPaymentRecord(record: PaymentRecord, contextAccountId: String): PaymentTransaction {
            return PaymentTransaction(
                    base = BaseTransaction.fromPaymentRecord(record, contextAccountId),
                    sourceAccount = record.sourceAccount ?: record.from ?: "",
                    destAccount = record.to ?: "",
                    senderFee = ComplexFee(
                            fixed = BigDecimalUtil.valueOf(record.sourceFixedFee),
                            percent = BigDecimalUtil.valueOf(record.sourcePaymentFee)
                    ),
                    recipientFee = ComplexFee(
                            fixed = BigDecimalUtil.valueOf(record.destFixedFee),
                            percent = BigDecimalUtil.valueOf(record.destPaymentFee)
                    ),
                    feePaidBySender = record.isFeeFromSource,
                    subject = record.subject
            )
        }
    }
}