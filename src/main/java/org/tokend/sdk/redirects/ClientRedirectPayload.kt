package org.tokend.sdk.redirects

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import okhttp3.HttpUrl
import org.tokend.sdk.factory.JsonApiToolsProvider
import org.tokend.sdk.utils.extentions.decodeBase64

/**
 * Holds client redirect data.
 * @see <a href="https://tokend.gitlab.io/docs/?http#client-redirects">Docs</a>
 */
class ClientRedirectPayload(
    @JsonProperty("type")
    val typeName: String,
    @JsonProperty("meta")
    val meta: JsonNode
) {
    val type: ClientRedirectType
        get() = ClientRedirectType.fromName(typeName)

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
                    JsonApiToolsProvider.getObjectMapper()
                        .readValue(it, ClientRedirectPayload::class.java)
                }
            } catch (e: Exception) {
                return null
            }
        }
    }
}