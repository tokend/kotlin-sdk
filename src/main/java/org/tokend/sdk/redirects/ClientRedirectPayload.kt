package org.tokend.sdk.redirects

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import okhttp3.HttpUrl
import org.tokend.sdk.factory.GsonFactory
import org.tokend.sdk.utils.extentions.decodeBase64

/**
 * Holds client redirect data.
 * @see <a href="https://tokend.gitlab.io/docs/?http#client-redirects">Docs</a>
 */
class ClientRedirectPayload(
        @SerializedName("type")
        val typeI: Int,
        @SerializedName("meta")
        val meta: JsonObject
) {
    val type: ClientRedirectType
        get() = ClientRedirectType.fromValue(typeI)

    companion object {
        private const val REDIRECT_PATH_SEGMENT = "r"

        @JvmStatic
        fun fromUrl(url: String): ClientRedirectPayload? {
            val parsedUrl = HttpUrl.parse(url) ?: return null
            val pathSegments = parsedUrl.pathSegments()
            val payloadIndex = pathSegments.indexOf(REDIRECT_PATH_SEGMENT) + 1
            val encodedPayload = pathSegments.getOrNull(payloadIndex) ?: return null

            return try {
                String(encodedPayload.decodeBase64()).let {
                    GsonFactory().getBaseGson().fromJson(it, ClientRedirectPayload::class.java)
                }
            } catch (e: Exception) {
                return null
            }
        }
    }
}