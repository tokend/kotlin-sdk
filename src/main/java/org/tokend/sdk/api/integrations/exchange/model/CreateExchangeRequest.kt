package org.tokend.sdk.api.integrations.exchange.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class CreateExchangeRequest(
        @SerializedName("source_balance")
        val sourceBalanceId: String,
        @SerializedName("destination_balance")
        val destBalanceId: String,
        @SerializedName("source_account")
        val sourceAccountId: String,
        @SerializedName("destination_account")
        val destAccountId: String,
        @SerializedName("amount")
        val amount: BigDecimal,
        @SerializedName("base")
        val baseAsset: String,
        @SerializedName("quote")
        val quoteAsset: String,
        @SerializedName("subject")
        val subject: String? = null
)