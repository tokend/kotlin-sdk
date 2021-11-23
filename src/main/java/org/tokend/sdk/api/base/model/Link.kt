package org.tokend.sdk.api.base.model


import com.fasterxml.jackson.annotation.JsonProperty
import java.net.URI
import java.net.URISyntaxException

/**
 * Represents links in responses.
 */
open class Link internal constructor(
    @JsonProperty("href")
    val href: String,
    @JsonProperty("templated")
    val isTemplated: Boolean
) {
    val uri: URI
        get() {
            try {
                return URI(href)
            } catch (e: URISyntaxException) {
                throw RuntimeException(e)
            }

        }
}
