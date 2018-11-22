package org.tokend.sdk.api.requests.model.preissuance

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class PreIssuanceCreateDetails(
        @SerializedName("asset")
        val asset: String,
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("signature")
        val signature: String,
        @SerializedName("reference")
        val reference: String
)