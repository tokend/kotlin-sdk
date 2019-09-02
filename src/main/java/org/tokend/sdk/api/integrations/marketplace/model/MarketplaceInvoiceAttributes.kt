package org.tokend.sdk.api.integrations.marketplace.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class MarketplaceInvoiceAttributes(
        @SerializedName("type")
        val type: String,
        @SerializedName("data")
        val data: JsonObject
)