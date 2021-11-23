package org.tokend.sdk.api.integrations.marketplace.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode


class MarketplaceInvoiceAttributes(
    @JsonProperty("type")
    val type: String,
    @JsonProperty("data")
    val data: JsonNode
)