package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

/**
 * Represents serializable holder of [T] value.
 */
data class NameValue<T>(
        @SerializedName("name")
        @JsonProperty("string")
        val name: String,
        @SerializedName("value")
        @JsonProperty("int")
        val value: T
)