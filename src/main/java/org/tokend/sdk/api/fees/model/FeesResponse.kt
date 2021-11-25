package org.tokend.sdk.api.fees.model

import com.fasterxml.jackson.annotation.JsonProperty


class FeesResponse(
    @JsonProperty("fees")
    val entries: Map<String, List<Fee>>?
)