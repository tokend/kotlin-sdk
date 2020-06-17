package org.tokend.sdk.api.integrations.recpayments.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.NameValue
import org.tokend.sdk.api.integrations.recpayments.model.SchedulePaymentRequest.Companion.toBalance
import org.tokend.sdk.api.integrations.recpayments.model.SchedulePaymentRequest.Companion.toCard
import java.math.BigDecimal

/**
 * @see toBalance
 * @see toCard
 */
class SchedulePaymentRequest
private constructor(
        destinationBalanceId: String,
        destinationCardNumber: String?,
        destinationType: Int,
        amount: BigDecimal,
        recurrenceRule: String,
        sourceAccountId: String,
        sourceBalanceId: String,
        sourceCardNumber: String,
        sendImmediately: Boolean
) {
    class Attributes(
            @SerializedName("destination_type")
            val destinationType: NameValue<Int>,
            @SerializedName("amount")
            val amount: BigDecimal,
            @SerializedName("r_rule")
            val recurrenceRule: String,
            @SerializedName("send_immediately")
            val sendImmediately: Boolean
    )

    @SerializedName("attributes")
    val attributes = Attributes(NameValue("hate_you", destinationType), amount,
            recurrenceRule, sendImmediately)

    @SerializedName("relationships")
    val relationships = mapOf(
            "destination_balance" to DataEntity(mapOf("id" to destinationBalanceId)),
            "destination_card" to DataEntity(mapOf("id" to destinationCardNumber)),
            "source_account" to DataEntity(mapOf("id" to sourceAccountId)),
            "source_balance" to DataEntity(mapOf("id" to sourceBalanceId)),
            "source_card" to DataEntity(mapOf("id" to sourceCardNumber))
    )

    companion object {
        @JvmStatic
        fun toBalance(destinationBalanceId: String,
                      amount: BigDecimal,
                      recurrenceRule: String,
                      sourceAccountId: String,
                      sourceBalanceId: String,
                      sourceCardNumber: String,
                      sendImmediately: Boolean): SchedulePaymentRequest {
            return SchedulePaymentRequest(
                    destinationBalanceId = destinationBalanceId,
                    destinationCardNumber = null,
                    destinationType = 1,
                    amount = amount,
                    recurrenceRule = recurrenceRule,
                    sourceAccountId = sourceAccountId,
                    sourceBalanceId = sourceBalanceId,
                    sourceCardNumber = sourceCardNumber,
                    sendImmediately = sendImmediately
            )
        }

        @JvmStatic
        fun toCard(destinationBalanceId: String,
                   destinationCardNumber: String,
                   amount: BigDecimal,
                   recurrenceRule: String,
                   sourceAccountId: String,
                   sourceBalanceId: String,
                   sourceCardNumber: String,
                   sendImmediately: Boolean): SchedulePaymentRequest {
            return SchedulePaymentRequest(
                    destinationBalanceId = destinationBalanceId,
                    destinationCardNumber = destinationCardNumber,
                    destinationType = 2,
                    amount = amount,
                    recurrenceRule = recurrenceRule,
                    sourceAccountId = sourceAccountId,
                    sourceBalanceId = sourceBalanceId,
                    sourceCardNumber = sourceCardNumber,
                    sendImmediately = sendImmediately
            )
        }
    }
}