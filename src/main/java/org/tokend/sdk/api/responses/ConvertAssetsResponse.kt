package org.tokend.sdk.api.responses

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class ConvertAssetsResponse {
    @SerializedName("amount")
    var amount: String? = null
}