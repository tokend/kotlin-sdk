package org.tokend.sdk.api.integrations.locator.model

import com.google.gson.annotations.SerializedName

class NearbyUserWithCards(
        @SerializedName("address")
        val accountId: String,
        @SerializedName("blob")
        val userData: MinimalUserDataWithCards
)