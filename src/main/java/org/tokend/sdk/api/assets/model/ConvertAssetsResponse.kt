package org.tokend.sdk.api.assets.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

open class ConvertAssetsResponse(
        @SerializedName("amount")
        var amount: BigDecimal
)