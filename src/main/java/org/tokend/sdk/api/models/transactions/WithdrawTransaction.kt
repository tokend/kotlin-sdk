package org.tokend.sdk.api.models.transactions

import com.google.gson.JsonObject
import org.tokend.sdk.api.models.Participant
import org.tokend.sdk.api.models.PaymentRecord

/**
 * Created by Oleg Koretsky on 12/19/17.
 */
class WithdrawTransaction(amount: String?,
                          asset: String?,
                          createdAt: String?,
                          val destinationAddress: String?,
                          val destinationAsset: String?,
                          val destinationAmount: String?,
                          fixedFee: String?,
                          paymentFee: String?,
                          type: String?,
                          state: PaymentState?,
                          id: String?,
                          pagingToken: String?,
                          participants: List<Participant>?) :
        Transaction(amount = amount,
                assetString = asset,
                createdAt = createdAt,
                type = type,
                state = state,
                id = id,
                pagingToken = pagingToken,
                fromBalance = null,
                toBalance = null,
                participants = participants,
                sourceFixedFee = fixedFee,
                sourcePaymentFee = paymentFee) {

    override fun isReceived(accountId: String) = false

    companion object {
        const val TYPE_WITHDRAW = "create_withdrawal_request"

        fun fromRecord(record: PaymentRecord): WithdrawTransaction {
            val externalDetails = record.externalDetails as? JsonObject
            val destinationAddress = externalDetails?.get("address")?.asString
            return WithdrawTransaction(
                    amount = record.amount,
                    asset = record.destAsset,
                    createdAt = record.ledgerCloseTime,
                    type = record.type,
                    state = PaymentState.fromCode(record.state),
                    id = record.id,
                    pagingToken = record.pagingToken,
                    participants = record.participants,
                    destinationAddress = destinationAddress,
                    destinationAmount = record.destAmount,
                    destinationAsset = record.destAsset,
                    fixedFee = record.fixedFee,
                    paymentFee = record.percentFee
            )
        }
    }
}