package org.tokend.sdk.api.identity.model

import com.google.gson.annotations.SerializedName

class MassEmailAccountKey(
        @SerializedName("id")
        val accountId: String
) {
    @SerializedName("type")
    val type = "accounts"
}