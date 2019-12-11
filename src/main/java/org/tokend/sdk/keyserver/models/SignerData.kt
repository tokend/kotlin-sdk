package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.generated.resources.SignerResource
import org.tokend.sdk.factory.JsonApiToolsProvider
import org.tokend.wallet.xdr.Uint32
import org.tokend.wallet.xdr.Uint64

/**
 * Account signer data holder
 */
class SignerData(
        @SerializedName("id")
        val id: String,
        @SerializedName("identity")
        val identity: Uint32,
        @SerializedName("weight")
        val weight: Uint32,
        @SerializedName("role_ids")
        val roleIds: Array<Uint64>,
        @SerializedName("details")
        val detailsJson: String?
) {
    @JvmOverloads
    constructor(source: SignerResource,
                objectMapper: ObjectMapper = JsonApiToolsProvider.getObjectMapper()) : this(
            identity = source.identity.toInt(),
            weight = source.weight.toInt(),
            roleIds = arrayOf(source.role.id.toLong()),
            detailsJson = source.details?.let {
                objectMapper.writeValueAsString(it)
            },
            id = source.id
    )

    @JvmOverloads
    constructor(source: org.tokend.sdk.api.ingester.generated.resources.SignerResource,
                objectMapper: ObjectMapper = JsonApiToolsProvider.getObjectMapper()) : this(
            identity = source.identity.toInt(),
            weight = source.weight.toInt(),
            roleIds = source.roles.mapNotNull { it.id.toLongOrNull() }.toTypedArray(),
            detailsJson = source.details?.let {
                objectMapper.writeValueAsString(it)
            },
            id = source.id
    )

    constructor(id: String, vararg roleIds: Uint64) : this(
            id = id,
            roleIds = roleIds.toTypedArray(),
            identity = DEFAULT_SIGNER_IDENTITY,
            weight = DEFAULT_SIGNER_WEIGHT,
            detailsJson = null
    )

    companion object {
        const val DEFAULT_SIGNER_IDENTITY = 0
        const val DEFAULT_SIGNER_WEIGHT = 1000
    }
}