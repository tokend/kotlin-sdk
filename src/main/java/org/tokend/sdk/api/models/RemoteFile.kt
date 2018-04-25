package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

/**
 * Created by Oleg Koretsky on 1/3/18.
 */
data class RemoteFile(@SerializedName("name")
                      val name: String? = null,
                      @SerializedName("type")
                      val mimeType: String? = null,
                      @SerializedName("url")
                      private val mUrl: String? = null,
                      @SerializedName("key")
                      private val key: String? = null) {

    val url: String?
        get() = mUrl

    val isImage: Boolean
        get() = mimeType?.contains("image/") ?: false
}