package org.tokend.sdk.api.wallets.model

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.keyserver.models.WalletData

class WalletResourceBody(
    @JsonProperty("data")
    val data: WalletData
) {
    @JsonProperty("included")
    val included: Collection<Any> = data.getFlattenRelationships()
}