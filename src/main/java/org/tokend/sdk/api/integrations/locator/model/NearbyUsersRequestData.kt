package org.tokend.sdk.api.integrations.locator.model

import com.google.gson.annotations.SerializedName

class NearbyUsersRequestData(
        @SerializedName("latitude")
        val lat: Double,
        @SerializedName("longitude")
        val lng: Double,
        @SerializedName("radius")
        val radiusKm: Double,
        @SerializedName("address")
        val accountId: String,
        @SerializedName("blob")
        val userData: MinimalUserData
)