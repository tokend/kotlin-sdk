package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

/**
 * Represents serializable holder of [T] value.
 */
data class NameValue<T>(
        @SerializedName("name")
        @get:JsonProperty("string")
        @param:JsonProperty("string")
        val name: String,
        @SerializedName("value")
        @get:JsonProperty("int")
        @param:JsonProperty("int")
        val value: T
)