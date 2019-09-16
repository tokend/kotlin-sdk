package org.tokend.sdk.api.integrations.locator.model

import com.google.gson.annotations.SerializedName

class NearbyUser(
        @SerializedName("address")
        val accountId: String,
        @SerializedName("blob")
        val userData: MinimalUserData
)