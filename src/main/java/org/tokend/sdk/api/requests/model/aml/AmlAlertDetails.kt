package org.tokend.sdk.api.requests.model.aml

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class AmlAlertDetails(
        @SerializedName("balance_id")
        val balanceId: String,
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("reason")
        val reason: String
)