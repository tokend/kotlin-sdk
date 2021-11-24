package org.tokend.sdk.api.integrations.marketplace.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.factory.JsonApiTools
import java.math.BigDecimal

sealed class MarketplaceInvoiceData {
    class Crypto(
        @JsonProperty("address")
        val address: String,
        @JsonProperty("amount")
        val amount: BigDecimal
    ) : MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "crypto_invoice"
        }
    }

    class Redirect(
        @JsonProperty("pay_url")
        val url: String
    ) : MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "redirect"
        }
    }

    class Internal(
        @JsonProperty("tx")
        val transactionEnvelope: String
    ) : MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "internal"
        }
    }

    companion object {
        @JvmStatic
        fun fromInvoiceAttributes(invoiceAttributes: MarketplaceInvoiceAttributes): MarketplaceInvoiceData {
            val clazz = when (invoiceAttributes.type) {
                Crypto.TYPE -> Crypto::class.java
                Redirect.TYPE -> Redirect::class.java
                Internal.TYPE -> Internal::class.java
                else -> throw IllegalArgumentException("Unknown marketplace invoice data type '${invoiceAttributes.type}'")
            }

            return JsonApiTools.objectMapper.treeToValue(invoiceAttributes.data, clazz)
        }
    }
}