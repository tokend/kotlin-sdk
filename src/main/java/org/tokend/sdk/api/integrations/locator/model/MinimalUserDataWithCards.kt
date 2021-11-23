package org.tokend.sdk.api.integrations.locator.model

import com.fasterxml.jackson.annotation.JsonProperty

class MinimalUserDataWithCards(
    avatar: String?,
    name: String,
    email: String,
    @JsonProperty("cards")
    val cards: List<String>
) : MinimalUserData(avatar, name, email)