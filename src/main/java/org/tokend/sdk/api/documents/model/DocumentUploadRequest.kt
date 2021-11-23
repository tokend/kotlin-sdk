package org.tokend.sdk.api.documents.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.api.base.model.DataEntity

class DocumentUploadRequest(
    @JsonProperty("type")
    val type: String,
    contentType: String,
    ownerAccountId: String
) {
    class Attributes(
        @JsonProperty("content_type")
        val contentType: String
    )

    class Relationships(ownerAccountId: String) {
        class Owner(
            @JsonProperty("id")
            val id: String
        )

        @JsonProperty("owner")
        val owner = DataEntity(Owner(ownerAccountId))
    }

    @JsonProperty("attributes")
    val attributes = Attributes(contentType)

    @JsonProperty("relationships")
    val relationships = Relationships(ownerAccountId)
}