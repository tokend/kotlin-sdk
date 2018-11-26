package org.tokend.sdk.api.authenticator.model

import com.google.gson.annotations.SerializedName

class AuthResult(
        @SerializedName("success")
        val isSuccessful: Boolean,
        @SerializedName("wallet_id")
        val walletId: String
)