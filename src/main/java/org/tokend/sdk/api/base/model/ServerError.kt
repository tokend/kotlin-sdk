package org.tokend.sdk.api.base.model

import com.google.gson.JsonElement
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Represents Server error.
 */
open class ServerError {
    @SerializedName("title")
    @Expose
    val title: String? = null
    @SerializedName("status")
    @Expose
    val status: Int? = null
    @SerializedName("detail")
    @Expose
    val detail: String? = null
    @SerializedName("meta")
    @Expose
    val meta: JsonElement? = null
}
