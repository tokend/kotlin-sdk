package org.tokend.sdk.api.documents.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity

class DocumentUploadRequest(
        @SerializedName("type")
        val type: String,
        contentType: String,
        ownerAccountId: String
) {
    class Attributes(
            @SerializedName("content_type")
            val contentType: String
    )

    class Relationships(ownerAccountId: String) {
        class Owner(
                @SerializedName("id")
                val id: String
        )

        @SerializedName("owner")
        val owner = DataEntity(Owner(ownerAccountId))
    }

    @SerializedName("attributes")
    val attributes = Attributes(contentType)

    @SerializedName("relationships")
    val relationships = Relationships(ownerAccountId)
}