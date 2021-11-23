package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ObjectNode

/**
 * Represents Server error.
 */
open class ServerError {
    @JsonProperty("title")
    val title: String? = null

    @JsonProperty("status")
    val status: Int? = null

    @JsonProperty("detail")
    val detail: String? = null

    @JsonProperty("meta")
    val meta: JsonNode? = null
}
