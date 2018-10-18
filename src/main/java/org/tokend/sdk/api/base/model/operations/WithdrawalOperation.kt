package org.tokend.sdk.api.base.model.operations

import com.google.gson.JsonObject
import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord
import org.tokend.sdk.utils.BigDecimalUtil
import java.math.BigDecimal

/**
 * Represents operation of withdrawal some amount of some asset
 * to the external payment system.
 */
open class WithdrawalOperation(
        private val base: BaseTransferOperation,
        override val asset: String,
        val destAddress: String?,
        val destAmount: BigDecimal,
        val fee: ComplexFee
) : TransferOperation by base {
    override val type = OperationType.WITHDRAWAL

    companion object {
        fun fromUnifiedOperationRecord(record: UnifiedOperationRecord,
                                       contextAccountId: String): WithdrawalOperation {
            val externalDetails = record.externalDetails as? JsonObject
            val destinationAddress = externalDetails?.get("address")?.asString

            val baseTransaction = BaseTransferOperation.fromPaymentRecord(record, contextAccountId)

            val fee =
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

            return WithdrawalOperation(
                    base = baseTransaction,
                    asset = record.destAsset ?: "",
                    destAddress = destinationAddress,
                    destAmount = BigDecimalUtil.valueOf(record.destAmount),
                    fee = fee
            )
        }
    }
}