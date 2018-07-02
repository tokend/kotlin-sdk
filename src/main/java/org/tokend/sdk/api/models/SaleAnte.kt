package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class SaleAnte(
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("asset_code")
        val asset: String
)