package org.tokend.sdk.api.base.model.transactions

import org.tokend.sdk.api.accounts.model.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil

/**
 * Represents operation of transferring some amount of some asset
 * between two accounts.
 */
open class PaymentTransaction(
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
            val baseTransaction = BaseTransaction.fromPaymentRecord(record, contextAccountId)

            val senderFee =
                    if (record.sourceFeeData != null)
                        ComplexFee(
                                fixed = record.sourceFeeData.fixedFee,
                                percent = record.sourceFeeData.paymentFee,
                                asset = record.sourceFeeData.asset
                        )
                    else
                        ComplexFee(
                                fixed = BigDecimalUtil.valueOf(record.sourceFixedFee),
                                percent = BigDecimalUtil.valueOf(record.sourcePaymentFee),
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
                                fixed = BigDecimalUtil.valueOf(record.destFixedFee),
                                percent = BigDecimalUtil.valueOf(record.destPaymentFee),
                                asset = baseTransaction.asset
                        )

            return PaymentTransaction(
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