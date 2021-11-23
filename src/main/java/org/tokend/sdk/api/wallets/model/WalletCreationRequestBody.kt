package org.tokend.sdk.api.wallets.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.keyserver.models.WalletCreationData

class WalletCreationRequestBody(
    @JsonProperty("data")
    val data: WalletCreationData
) {
    @JsonProperty("included")
    val included: Collection<Any> = data.getFlattenRelationships()
}