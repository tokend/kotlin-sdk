package org.tokend.sdk.api.integrations.locator.model

import com.fasterxml.jackson.annotation.JsonProperty

class NearbyUsersRequestData(
    @JsonProperty("latitude")
    val lat: Double,
    @JsonProperty("longitude")
    val lng: Double,
    @JsonProperty("radius")
    val radiusKm: Double,
    @JsonProperty("address")
    val accountId: String,
    @JsonProperty("blob")
    val userData: MinimalUserData
)