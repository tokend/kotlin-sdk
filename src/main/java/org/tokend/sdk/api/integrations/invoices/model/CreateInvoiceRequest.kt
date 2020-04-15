package org.tokend.sdk.api.integrations.invoices.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import java.math.BigDecimal

class CreateInvoiceRequest(
        amount: BigDecimal,
        assetCode: String,
        destinationBalanceId: String,
        targetAccountId: String,
        selfAccountId: String,
        subject: String
) {
    class Attributes(
            @SerializedName("amount")
            val amount: BigDecimal,
            @SerializedName("subject")
            val subject: String
    )

    @SerializedName("attributes")
    val attributes = Attributes(amount, subject)

    @SerializedName("relationships")
    val relationships = mapOf(
            "asset" to DataEntity(mapOf("id" to assetCode)),
            "target" to DataEntity(mapOf("id" to targetAccountId)),
            "requestor" to DataEntity(mapOf("id" to selfAccountId)),
            "destination_balance" to DataEntity(mapOf("id" to destinationBalanceId))
    )
}