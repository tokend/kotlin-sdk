package org.tokend.sdk.api.blobs.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.api.base.model.DataEntity

class BlobCreationRequestBody(
    blob: Blob,
    ownerAccountId: String?
) : Blob("", blob.typeName, Attributes(blob.valueString)) {
    class Relationships(
        @JsonProperty("owner")
        val owner: DataEntity<Owner>
    ) {
        class Owner(
            @JsonProperty("id")
            val accountId: String
        )
    }

    @JsonProperty("relationships")
    val relationships =
        if (ownerAccountId != null)
            Relationships(DataEntity(Relationships.Owner(ownerAccountId)))
        else
            null
}