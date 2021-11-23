package org.tokend.sdk.api.integrations.invoices.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.api.base.model.DataEntity
import java.math.BigDecimal

class CreateInvoiceRequest(
    amount: BigDecimal,
    assetCode: String,
    destinationCardNumber: String,
    targetAccountId: String,
    selfAccountId: String,
    subject: String
) {
    class Attributes(
        @JsonProperty("amount")
        val amount: BigDecimal,
        @JsonProperty("subject")
        val subject: String
    )

    @JsonProperty("attributes")
    val attributes = Attributes(amount, subject)

    @JsonProperty("relationships")
    val relationships = mapOf(
        "asset" to DataEntity(mapOf("id" to assetCode)),
        "target" to DataEntity(mapOf("id" to targetAccountId)),
        "requestor" to DataEntity(mapOf("id" to selfAccountId)),
        "destination_card" to DataEntity(mapOf("id" to destinationCardNumber))
    )
}