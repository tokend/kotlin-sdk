package org.tokend.sdk.api.responses

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Oleg Koretsky on 11/21/17.
 */

class ServerError {
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
    val meta: Map<String, Any>? = null

    companion object {
        val BAD_REQUEST = 400
        val UNAUTHORIZED = 401
        val FORBIDDEN = 403
        val NOT_FOUND = 404
        val CONFLICT = 409
    }
}
