package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Represents reference to remote file.
 * @see AssetDetails
 * @see SaleDetails
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class RemoteFile(@SerializedName("key")
                      @JsonProperty("key")
                      val key: String,
                      @SerializedName("name")
                      @JsonProperty("name")
                      val name: String? = null,
                      @JsonProperty("mime_type")
                      mimeType: String? = null
) : Serializable {
    // Legacy.
    @SerializedName("url")
    protected val mUrl: String? = null

    // Legacy.
    @SerializedName("type")
    protected val mType: String? = null

    @SerializedName("mime_type")
    protected val mMimeType: String? = mimeType

    open fun getUrl(storageRoot: String?): String? {
        return if (storageRoot != null && key.isNotEmpty())
            storageRoot + key
        else
            mUrl
    }

    open val mimeType: String?
        get() = mMimeType?.takeIf { it.isNotEmpty() } ?: mType

    open val isImage: Boolean
        get() = mimeType?.contains("image/") ?: false

    override fun equals(other: Any?): Boolean {
        return other is RemoteFile && other.key == this.key
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}