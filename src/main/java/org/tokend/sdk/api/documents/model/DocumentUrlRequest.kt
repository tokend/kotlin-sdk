package org.tokend.sdk.api.documents.model

import com.fasterxml.jackson.annotation.JsonProperty

class DocumentUrlRequest(
    @JsonProperty("attributes")
    val attributes: Attributes
) {
    class Attributes(
        @JsonProperty("url")
        val url: String
    )
}