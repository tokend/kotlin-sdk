package org.tokend.sdk.api.integrations.locator.model

import com.fasterxml.jackson.annotation.JsonProperty

class NearbyUserWithCards(
    @JsonProperty("address")
    val accountId: String,
    @JsonProperty("blob")
    val userData: MinimalUserDataWithCards
)