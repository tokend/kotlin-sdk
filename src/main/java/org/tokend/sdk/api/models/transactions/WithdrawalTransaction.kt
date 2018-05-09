package org.tokend.sdk.api.models.transactions

import com.google.gson.JsonObject
import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.utils.BigDecimalUtil

class WithdrawalTransaction(
        base: BaseTransaction,
        override val asset: String,
        val destAddress: String?,
        val fee: ComplexFee
) : Transaction by base {
    override val type = TransactionType.WITHDRAWAL

    companion object {
        fun fromPaymentRecord(record: PaymentRecord): WithdrawalTransaction {
            val externalDetails = record.externalDetails as? JsonObject
            val destinationAddress = externalDetails?.get("address")?.asString

            return WithdrawalTransaction(
                    base = BaseTransaction.fromPaymentRecord(record),
                    asset = record.destAsset ?: "",
                    destAddress = destinationAddress,
                    fee = ComplexFee(
                            fixed = BigDecimalUtil.valueOf(record.sourceFixedFee),
                            percent = BigDecimalUtil.valueOf(record.sourcePaymentFee)
                    )
            )
        }
    }
}