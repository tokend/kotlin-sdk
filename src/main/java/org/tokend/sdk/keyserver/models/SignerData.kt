package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.databind.ObjectMapper
import org.tokend.sdk.api.generated.resources.SignerResource
import org.tokend.sdk.factory.JsonApiToolsProvider
import org.tokend.wallet.xdr.Uint32
import org.tokend.wallet.xdr.Uint64

/**
 * Account signer data holder
 */
class SignerData(
        val id: String,
        val identity: Uint32,
        val weight: Uint32,
        val roleId: Uint64,
        val detailsJson: String?
) {
    @JvmOverloads
    constructor(source: SignerResource,
                objectMapper: ObjectMapper = JsonApiToolsProvider.getObjectMapper()) : this(
            identity = source.identity.toInt(),
            weight = source.weight.toInt(),
            roleId = source.role.id.toLong(),
            detailsJson = source.details?.let {
                objectMapper.writeValueAsString(it)
            },
            id = source.id
    )
}