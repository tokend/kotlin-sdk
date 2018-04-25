package org.tokend.sdk.api.models.transactions

import com.google.gson.JsonObject
import org.tokend.sdk.api.models.Participant
import org.tokend.sdk.api.models.PaymentRecord

/**
 * Created by Oleg Koretsky on 12/15/17.
 */
class EmissionTransaction(amount: String?,
                          asset: String?,
                          createdAt: String?,
                          val reference: String?,
                          type: String?,
                          state: PaymentState?,
                          id: String?,
                          pagingToken: String?,
                          participants: List<Participant>?,
                          val cause: String?) :
        Transaction(amount = amount,
                assetString = asset,
                createdAt = createdAt,
                type = type,
                state = state,
                id = id,
                pagingToken = pagingToken,
                fromBalance = null,
                toBalance = null,
                participants = participants) {

    override fun isReceived(accountId: String) = true

    companion object {
        const val TYPE_ISSUANCE = "create_issuance_request"
        const val CAUSE_AIRDROP_EARLYBIRD = "airdrop"
        const val CAUSE_AIRDROP_KYC = "airdrop-for-kyc"

        fun fromRecord(record: PaymentRecord): EmissionTransaction {
            val externalDetails = record.externalDetails as? JsonObject
            val cause = externalDetails?.get("cause")?.asString

            return EmissionTransaction(
                    amount = record.amount,
                    asset = record.asset,
                    createdAt = record.ledgerCloseTime,
                    type = record.type,
                    state = PaymentState.fromCode(record.state),
                    id = record.id,
                    pagingToken = record.pagingToken,
                    participants = record.participants,
                    reference = record.reference,
                    cause = cause
            )
        }
    }
}