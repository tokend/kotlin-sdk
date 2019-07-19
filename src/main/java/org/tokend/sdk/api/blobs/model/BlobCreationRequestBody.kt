package org.tokend.sdk.api.blobs.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity

class BlobCreationRequestBody(
        blob: Blob,
        ownerAccountId: String?
) : Blob("", blob.typeName, Attributes(blob.valueString)) {
    class Relationships(
            @SerializedName("owner")
            val owner: DataEntity<Owner>
    ) {
        class Owner(
                @SerializedName("id")
                val accountId: String
        )
    }

    @SerializedName("relationships")
    val relationships =
            if (ownerAccountId != null)
                Relationships(DataEntity(Relationships.Owner(ownerAccountId)))
            else
                null
}