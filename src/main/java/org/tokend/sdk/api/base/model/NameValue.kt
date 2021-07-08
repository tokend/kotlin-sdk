package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

/**
 * Represents serializable holder of [T] value.
 */
data class NameValue<T>(
        @SerializedName("name")
        @get:JsonProperty("name")
        @param:JsonProperty("name")
        val name: String,
        @SerializedName("value")
        @get:JsonProperty("value")
        @param:JsonProperty("value")
        val value: T
)