package org.tokend.sdk.api.accounts.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.keyserver.models.SignerData
import org.tokend.sdk.keyserver.models.WalletRelationship

class CreateAccountRequestBody(
        accountId: String,
        signers: Collection<SignerData>
) {
    @JsonProperty("included")
    val included = signers.map(WalletRelationship.Companion::signer)

    @JsonProperty("data")
    val data = mapOf(
            "id" to accountId,
            "relationships" to mapOf(
                    "signers" to DataEntity(included.map {
                        mapOf("id" to it.id, "type" to it.type)
                    })
            )
    )
}