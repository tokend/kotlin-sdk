package org.tokend.sdk.api.integrations.escrow.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceData

class CreatedEscrowAttributes(
        @SerializedName("invoice")
        val invoice: MarketplaceInvoiceData.Redirect
)