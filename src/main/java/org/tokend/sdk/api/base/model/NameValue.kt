package org.tokend.sdk.api.base.model

import com.google.gson.annotations.SerializedName

/**
 * Represents serializable holder of [T] value.
 */
data class NameValue<T>(
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: T
)