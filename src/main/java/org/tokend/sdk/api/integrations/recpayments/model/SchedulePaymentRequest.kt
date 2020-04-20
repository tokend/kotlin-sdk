package org.tokend.sdk.api.integrations.recpayments.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.model.NameValue
import java.math.BigDecimal

class SchedulePaymentRequest
private constructor(
        destinationAccountId: String?,
        destinationBalanceId: String?,
        destinationType: Int,
        amount: BigDecimal,
        recurrenceRule: String,
        sourceAccountId: String,
        sourceBalanceId: String,
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
            "destination_account" to DataEntity(mapOf("id" to destinationAccountId)),
            "destination_balance" to DataEntity(mapOf("id" to destinationBalanceId)),
            "source_account" to DataEntity(mapOf("id" to sourceAccountId)),
            "source_balance" to DataEntity(mapOf("id" to sourceBalanceId))
    )

    companion object {
        @JvmStatic
        fun toAccount(destinationAccountId: String,
                      amount: BigDecimal,
                      recurrenceRule: String,
                      sourceAccountId: String,
                      sourceBalanceId: String,
                      sendImmediately: Boolean): SchedulePaymentRequest {
            return SchedulePaymentRequest(
                    destinationAccountId = destinationAccountId,
                    destinationBalanceId = null,
                    destinationType = 0,
                    amount = amount,
                    recurrenceRule = recurrenceRule,
                    sourceAccountId = sourceAccountId,
                    sourceBalanceId = sourceBalanceId,
                    sendImmediately = sendImmediately
            )
        }

        @JvmStatic
        fun toBalance(destinationBalanceId: String,
                      amount: BigDecimal,
                      recurrenceRule: String,
                      sourceAccountId: String,
                      sourceBalanceId: String,
                      sendImmediately: Boolean): SchedulePaymentRequest {
            return SchedulePaymentRequest(
                    destinationAccountId = null,
                    destinationBalanceId = destinationBalanceId,
                    destinationType = 0,
                    amount = amount,
                    recurrenceRule = recurrenceRule,
                    sourceAccountId = sourceAccountId,
                    sourceBalanceId = sourceBalanceId,
                    sendImmediately = sendImmediately
            )
        }
    }
}