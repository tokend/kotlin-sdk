package org.tokend.sdk.api.integrations.escrow.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceData

class CreatedEscrowAttributes(
    @JsonProperty("invoice")
    val invoice: MarketplaceInvoiceData.Redirect
)