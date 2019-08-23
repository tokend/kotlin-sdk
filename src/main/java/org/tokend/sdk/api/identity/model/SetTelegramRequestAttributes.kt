package org.tokend.sdk.api.identity.model

import com.google.gson.annotations.SerializedName

class SetTelegramRequestAttributes(
        @SerializedName("username")
        val username: String
)