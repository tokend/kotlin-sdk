package org.tokend.sdk.api.base.model

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

/**
 * Represents reference to remote file,
 * such as avatar, logo, terms, etc.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
open class RemoteFile(
    /**
     * Unique identifier of the file
     */
    @JsonProperty("key")
    val key: String,

    /**
     * Original name of the file with an extension
     */
    @JsonProperty("name")
    val name: String,

    /**
     * MIME (content) type of the file
     *
     * @see <a href="https://www.iana.org/assignments/media-types/media-types.xhtml">List of MIME types</a>
     */
    @JsonProperty("mime_type")
    @JsonAlias("mimeType")
    val mimeType: String
) : Serializable {
    @JsonIgnore
    open fun getUrl(storageRoot: String) =
        storageRoot +
                (if (storageRoot.endsWith('/')) "" else "/") + key

    @get:JsonIgnore
    open val isImage: Boolean
        get() = mimeType.contains("image/")

    override fun equals(other: Any?): Boolean {
        return other is RemoteFile && other.key == this.key
    }

    override fun hashCode(): Int {
        return key.hashCode()
    }
}

@Deprecated(
    message = "Better push on JS devs to not send " +
            "invalid file structures as 'no file' instead of using this method",
    replaceWith = ReplaceWith(" == null")
)
val RemoteFile?.isReallyNullOrNullAccordingToTheJavascript: Boolean
    get() = this == null || this.key.isEmpty() || this.name.isEmpty() || this.mimeType.isEmpty()