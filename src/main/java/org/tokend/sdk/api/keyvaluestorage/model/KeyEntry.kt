package org.tokend.sdk.api.keyvaluestorage.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.NameValue

open class KeyEntry(
        @SerializedName("key") val key: String,
        @SerializedName("type") val type: NameValue<Int>,
        @SerializedName("uint32_value") val uint32Value: Int,
        @SerializedName("string_value") val stringValue: String
)