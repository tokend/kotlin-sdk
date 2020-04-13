package org.tokend.sdk.api.integrations.invoices.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import java.math.BigDecimal

class CreateInvoiceRequest(
        amount: BigDecimal,
        assetCode: String,
        destinationBalanceId: String,
        targetAccountId: String,
        subject: Any
) {
    class Attributes(
            @SerializedName("amount")
            val amount: BigDecimal,
            @SerializedName("destination")
            val destination: String,
            @SerializedName("subject")
            val subject: Any
    )

    @SerializedName("attributes")
    val attributes = Attributes(amount, destinationBalanceId, subject)

    @SerializedName("relationships")
    val relationships = mapOf(
            "asset" to DataEntity(mapOf("id" to assetCode)),
            "target" to DataEntity(mapOf("id" to targetAccountId))
    )
}