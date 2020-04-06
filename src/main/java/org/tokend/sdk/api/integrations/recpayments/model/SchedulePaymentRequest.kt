package org.tokend.sdk.api.integrations.recpayments.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import java.math.BigDecimal

class SchedulePaymentRequest(
        destinationAccountId: String,
        destinationBalanceId: String,
        destinationType: Int,
        amount: BigDecimal,
        recurrenceRule: String
) {
    class Attributes(
            @SerializedName("destination_type")
            val destinationType: Int,
            @SerializedName("amount")
            val amount: BigDecimal,
            @SerializedName("r_rule")
            val recurrenceRule: String
    )

    @SerializedName("attributes")
    val attributes = Attributes(destinationType, amount, recurrenceRule)

    @SerializedName("relationships")
    val relationships = mapOf(
            "destination_account" to DataEntity(mapOf("id" to destinationAccountId)),
            "destination_balance" to DataEntity(mapOf("id" to destinationBalanceId))
    )
}