package org.tokend.sdk.api.integrations.invitations.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import java.util.*

class CreateInvitationRequest(
        host: String,
        guest: String,
        placeAsset: String,
        from: Date,
        to: Date,
        details: Any
) {
    private class Attributes(
            @SerializedName("from")
            val from: Date,
            @SerializedName("to")
            val to: Date,
            @SerializedName("details")
            val details: Any
    )

    @SerializedName("attributes")
    private val attributes = Attributes(from, to, details)

    @SerializedName("relationships")
    val relationships = mapOf(
            "host" to DataEntity(mapOf("id" to host)),
            "guest" to DataEntity(mapOf("id" to guest)),
            "place" to DataEntity(mapOf("id" to placeAsset))
    )
}