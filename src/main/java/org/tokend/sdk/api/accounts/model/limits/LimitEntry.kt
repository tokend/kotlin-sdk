package org.tokend.sdk.api.accounts.model.limits

import com.fasterxml.jackson.annotation.JsonProperty


data class LimitEntry(
        @JsonProperty("limit")
        val limit: Limit,
        @JsonProperty("statistics")
        val statistics: Statistics
)