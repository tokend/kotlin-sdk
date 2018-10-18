package org.tokend.sdk.api.base.model.operations

import com.google.gson.JsonObject
import org.tokend.sdk.api.accounts.model.UnifiedOperationRecord

/**
 * Represents an issuance of some amount of some asset.
 * It may be a deposit or a bonus.
 */
class IssuanceOperation(
        private val base: BaseTransferOperation,
        val cause: String?,
        val reference: String?
) : TransferOperation by base {
    override val type = OperationType.ISSUANCE

    companion object {
        fun fromUnifiedOperationRecord(record: UnifiedOperationRecord,
                                       contextAccountId: String): IssuanceOperation {
            val externalDetails = record.externalDetails as? JsonObject
            val cause = externalDetails?.get("cause")?.asString

            return IssuanceOperation(
                    base = BaseTransferOperation.fromPaymentRecord(record, contextAccountId),
                    cause = cause,
                    reference = record.reference
            )
        }
    }
}