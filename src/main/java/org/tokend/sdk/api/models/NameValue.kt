package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

data class NameValue<T>(
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: T
)