package org.tokend.sdk.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ServerResponse<out DataType, out ErrorType> {
    @SerializedName("data")
    @Expose
    val data: DataType? = null
    @SerializedName("errors")
    @Expose
    val errors: List<ErrorType>? = null

    val isSuccess: Boolean
        get() = errors == null || errors.isEmpty()
}
