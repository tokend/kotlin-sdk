package org.tokend.sdk.api.integrations.marketplace.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

sealed class MarketplaceInvoiceData {
    class Crypto(
            @SerializedName("address")
            val address: String,
            @SerializedName("amount")
            val amount: BigDecimal
    ) : MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "crypto"
        }
    }

    class Redirect(
            @SerializedName("pay_url")
            val url: String
    ) : MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "redirect"
        }
    }
}