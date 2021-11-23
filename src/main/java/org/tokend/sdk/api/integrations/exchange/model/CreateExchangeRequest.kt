package org.tokend.sdk.api.integrations.exchange.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

class CreateExchangeRequest(
    @JsonProperty("source_balance")
    val sourceBalanceId: String,
    @JsonProperty("destination_balance")
    val destBalanceId: String,
    @JsonProperty("source_account")
    val sourceAccountId: String,
    @JsonProperty("destination_account")
    val destAccountId: String,
    @JsonProperty("amount")
    val amount: BigDecimal,
    @JsonProperty("base")
    val baseAsset: String,
    @JsonProperty("quote")
    val quoteAsset: String,
    @JsonProperty("subject")
    val subject: String? = null,
    @JsonProperty("reference")
    val reference: String? = null
)