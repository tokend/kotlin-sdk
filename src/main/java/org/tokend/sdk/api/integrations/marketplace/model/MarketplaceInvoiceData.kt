package org.tokend.sdk.api.integrations.marketplace.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.factory.GsonFactory
import java.math.BigDecimal

sealed class MarketplaceInvoiceData {
    class Crypto(
            @SerializedName("address")
            val address: String,
            @SerializedName("amount")
            val amount: BigDecimal
    ) : MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "crypto_invoice"
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

    class Internal(
            @SerializedName("tx")
            val transactionEnvelope: String
    ): MarketplaceInvoiceData() {
        companion object {
            const val TYPE = "internal"
        }
    }

    companion object {
        @JvmStatic
        fun fromInvoiceAttributes(invoiceAttributes: MarketplaceInvoiceAttributes): MarketplaceInvoiceData {
            val gson = GsonFactory().getBaseGson()

            val clazz = when (invoiceAttributes.type) {
                MarketplaceInvoiceData.Crypto.TYPE -> MarketplaceInvoiceData.Crypto::class.java
                MarketplaceInvoiceData.Redirect.TYPE -> MarketplaceInvoiceData.Redirect::class.java
                MarketplaceInvoiceData.Internal.TYPE -> MarketplaceInvoiceData.Internal::class.java
                else -> throw IllegalArgumentException("Unknown marketplace invoice data type '${invoiceAttributes.type}'")
            }

            return gson.fromJson(invoiceAttributes.data, clazz)
        }
    }
}