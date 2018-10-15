package org.tokend.sdk.api.base.model.transactions

import com.google.gson.JsonObject
import org.tokend.sdk.api.accounts.model.PaymentRecord

class IssuanceTransaction(
        private val base: BaseTransaction,
        val cause: String?,
        val reference: String?
) : Transaction by base {
    override val type = TransactionType.ISSUANCE

    companion object {
        fun fromPaymentRecord(record: PaymentRecord,
                              contextAccountId: String): IssuanceTransaction {
            val externalDetails = record.externalDetails as? JsonObject
            val cause = externalDetails?.get("cause")?.asString

            return IssuanceTransaction(
                    base = BaseTransaction.fromPaymentRecord(record, contextAccountId),
                    cause = cause,
                    reference = record.reference
            )
        }
    }
}