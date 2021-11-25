package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Represents serializable holder of [T] value.
 */
data class NameValue<T>(
    @JsonProperty("name")
    val name: String,
    @JsonProperty("value")
    val value: T
)