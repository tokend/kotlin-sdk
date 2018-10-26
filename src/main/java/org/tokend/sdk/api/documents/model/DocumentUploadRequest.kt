package org.tokend.sdk.api.documents.model

import com.google.gson.annotations.SerializedName

class DocumentUploadRequest(
        @SerializedName("type")
        val type: String,
        @SerializedName("attributes")
        val attributes: Attributes
) {
    constructor(type: String, contentType: String) : this(type, Attributes(contentType))

    class Attributes(
            @SerializedName("content_type")
            val contentType: String
    )
}