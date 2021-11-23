package org.tokend.sdk.api.integrations.invitations.model

import com.fasterxml.jackson.annotation.JsonProperty
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
    class Attributes(
        @JsonProperty("from")
        val from: Date,
        @JsonProperty("to")
        val to: Date,
        @JsonProperty("details")
        val details: Any
    )

    @JsonProperty("attributes")
    val attributes = Attributes(from, to, details)

    @JsonProperty("relationships")
    val relationships = mapOf(
        "host" to DataEntity(mapOf("id" to host)),
        "guest" to DataEntity(mapOf("id" to guest)),
        "place" to DataEntity(mapOf("id" to placeAsset))
    )
}