package org.tokend.sdk.api.models.transactions

import com.google.gson.JsonObject
import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

open class WithdrawalTransaction(
        private val base: BaseTransaction,
        override val asset: String,
        val destAddress: String?,
        val destAmount: BigDecimal,
        val fee: ComplexFee
) : Transaction by base {
    override val type = TransactionType.WITHDRAWAL

    companion object {
        fun fromPaymentRecord(record: PaymentRecord,
                              contextAccountId: String): WithdrawalTransaction {
            val externalDetails = record.externalDetails as? JsonObject
            val destinationAddress = externalDetails?.get("address")?.asString

            val baseTransaction = BaseTransaction.fromPaymentRecord(record, contextAccountId)

            val fee =
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

            return WithdrawalTransaction(
                    base = baseTransaction,
                    asset = record.destAsset ?: "",
                    destAddress = destinationAddress,
                    destAmount = BigDecimalUtil.valueOf(record.destAmount),
                    fee = fee
            )
        }
    }
}