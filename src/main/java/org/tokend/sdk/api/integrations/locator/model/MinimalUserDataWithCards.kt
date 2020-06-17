package org.tokend.sdk.api.integrations.locator.model

import com.google.gson.annotations.SerializedName

class MinimalUserDataWithCards(
        avatar: String?,
        name: String,
        email: String,
        @SerializedName("cards")
        val cards: List<String>
) : MinimalUserData(avatar, name, email)