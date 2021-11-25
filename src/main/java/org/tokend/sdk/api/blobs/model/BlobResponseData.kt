package org.tokend.sdk.api.blobs.model

import com.fasterxml.jackson.annotation.JsonProperty

class BlobResponseData(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("type")
    val type: String,
    @JsonProperty("attributes")
    val attributes: Map<String, String>
) {
    fun toBlob(): Blob = Blob(
        id = id,
        typeName = type,
        valueString = attributes.getValue("value")
    )
}