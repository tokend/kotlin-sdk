package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

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
                      val key: String? = null) : Serializable {

    fun getUrl(storageRoot: String?): String? {
        return if (storageRoot != null && key != null)
            storageRoot + key
        else
            mUrl
    }

    val isImage: Boolean
        get() = mimeType?.contains("image/") ?: false
}