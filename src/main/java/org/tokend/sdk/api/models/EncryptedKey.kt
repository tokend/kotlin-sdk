package org.tokend.sdk.api.models

import com.google.gson.annotations.SerializedName

open class EncryptedKey(
        @SerializedName("account_id")
        var accountId: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("salt")
        var salt: String,
        @SerializedName("keychain_data")
        var keychainData: String
)