package org.tokend.sdk.api.accounts.model.limits

import com.fasterxml.jackson.annotation.JsonProperty

class LimitsResponse(
        @JsonProperty("limits")
        val entries: List<LimitEntry>?
)