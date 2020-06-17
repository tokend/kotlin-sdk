package org.tokend.sdk.api.integrations.locator.model

import com.google.gson.annotations.SerializedName

open class MinimalUserData(
        @SerializedName("avatar_url")
        val avatar: String?,
        @SerializedName("name")
        val name: String,
        @SerializedName("email")
        val email: String
)