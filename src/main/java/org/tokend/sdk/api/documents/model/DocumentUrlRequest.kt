package org.tokend.sdk.api.documents.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

class DocumentUrlRequest(
        @SerializedName("attributes")
        @get:JsonProperty("attributes")
        @param:JsonProperty("attributes")
        val attributes: Attributes
) {
    class Attributes(
            @SerializedName("url")
            @get:JsonProperty("url")
            @param:JsonProperty("url")
            val url: String
    )
}