package org.tokend.sdk.api.requests.model.issuance

import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class IssuanceCreateDetails(
        @SerializedName("asset")
        val asset: String,
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("receiver")
        val receiver: String,
        @SerializedName("external_details")
        val externalDetails: JsonElement
)