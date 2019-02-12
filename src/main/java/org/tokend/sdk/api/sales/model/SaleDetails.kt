package org.tokend.sdk.api.sales.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName
import okhttp3.HttpUrl
import org.tokend.sdk.api.base.model.RemoteFile
import org.tokend.sdk.utils.HashCodes
import java.io.Serializable

@Deprecated("Sale details are system-specific and will be removed from SDK")
open class SaleDetails(@SerializedName("name")
                       @JsonProperty("name")
                       val name: String,
                       @SerializedName("short_description")
                       @JsonProperty("short_description")
                       val shortDescription: String,
                       @SerializedName("description")
                       @JsonProperty("description")
                       val descriptionBlob: String,
                       @SerializedName("logo")
                       @JsonProperty("logo")
                       open val logo: RemoteFile,
                       @SerializedName("youtube_video_id")
                       @JsonProperty("youtube_video_id")
                       private val mYoutubeVideo: String? = null) : Serializable {
    open val youtubeVideo: String?
        get() = if (mYoutubeVideo.isNullOrEmpty()) null else mYoutubeVideo

    open val youtubeVideoId: String?
        get() {
            val video = youtubeVideo ?: return null

            return when {
                video.contains("youtube") ->
                    try {
                        HttpUrl.parse(video).queryParameter("v")
                    } catch (e: Exception) {
                        null
                    }
                video.contains("youtu.be") ->
                    try {
                        HttpUrl.parse(video).pathSegments().lastOrNull()
                    } catch (e: Exception) {
                        null
                    }
                else -> video
            }
        }

    open fun getYoutubeVideoUrl(mobile: Boolean): String? {
        return youtubeVideoId?.let { "https://${if (mobile) "m." else ""}youtube.com/watch?v=$it" }
    }

    open val youtubeVideoPreviewImage: String?
        get() = youtubeVideoId?.let { "https://img.youtube.com/vi/$it/hqdefault.jpg" }

    override fun equals(other: Any?): Boolean {
        return other is SaleDetails
                && other.name == this.name
                && other.descriptionBlob == this.descriptionBlob
                && other.shortDescription == this.shortDescription
                && other.youtubeVideo == this.youtubeVideo
    }

    override fun hashCode(): Int {
        return HashCodes.ofMany(name, descriptionBlob, shortDescription, youtubeVideo)
    }
}