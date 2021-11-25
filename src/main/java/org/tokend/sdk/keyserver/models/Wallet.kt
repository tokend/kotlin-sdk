package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class Wallet(
    @JsonProperty("data")
    val data: WalletData,
    @JsonProperty("included")
    private val included: List<WalletData.Session>
) {
    @get:JsonIgnore
    val session: WalletData.Session
        get() = included.first()
}