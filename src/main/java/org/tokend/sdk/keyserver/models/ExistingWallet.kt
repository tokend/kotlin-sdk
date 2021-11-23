package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class ExistingWallet(
    @JsonProperty("data")
    val data: ExistingWalletData,
    @JsonProperty("included")
    private val included: List<ExistingWalletData.Session>
) {
    @get:JsonIgnore
    val session: ExistingWalletData.Session
        get() = included.first()
}