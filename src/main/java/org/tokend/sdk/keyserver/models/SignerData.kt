package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.ObjectMapper
import org.tokend.sdk.api.v3.model.generated.resources.SignerResource
import org.tokend.sdk.factory.JsonApiTools
import org.tokend.wallet.xdr.Uint32
import org.tokend.wallet.xdr.Uint64

/**
 * Account signer data holder
 */
class SignerData(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("identity")
    val identity: Uint32,
    @JsonProperty("weight")
    val weight: Uint32,
    @JsonProperty("role_id")
    val roleId: Uint64,
    @JsonProperty("details")
    val detailsJson: String?
) {
    @JvmOverloads
    constructor(
        source: SignerResource,
        objectMapper: ObjectMapper = JsonApiTools.objectMapper
    ) : this(
        identity = source.identity.toInt(),
        weight = source.weight.toInt(),
        roleId = source.role.id.toLong(),
        detailsJson = source.details?.let {
            objectMapper.writeValueAsString(it)
        },
        id = source.id
    )

    constructor(id: String, roleId: Uint64) : this(
        id = id,
        roleId = roleId,
        identity = DEFAULT_SIGNER_IDENTITY,
        weight = DEFAULT_SIGNER_WEIGHT,
        detailsJson = null
    )

    companion object {
        const val DEFAULT_SIGNER_IDENTITY = 0
        const val DEFAULT_SIGNER_WEIGHT = 1000
    }
}