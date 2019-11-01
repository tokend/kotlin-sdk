package org.tokend.sdk.api.accounts.model

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.keyserver.models.SignerData
import org.tokend.sdk.keyserver.models.WalletRelation

class CreateAccountRequestBody(
        accountId: String,
        signers: Collection<SignerData>
) {
    @SerializedName("included")
    val included = signers.map(WalletRelation.Companion::signer)

    @SerializedName("data")
    val data = mapOf(
            "id" to accountId,
            "relationships" to mapOf(
                    "signers" to DataEntity(included.map {
                        mapOf("id" to it.id, "type" to it.type)
                    })
            )
    )
}