package org.tokend.sdk.api.integrations.locator.model

import com.fasterxml.jackson.annotation.JsonProperty

open class MinimalUserData(
    @JsonProperty("avatar_url")
    val avatar: String?,
    @JsonProperty("name")
    val name: String,
    @JsonProperty("email")
    val email: String
)