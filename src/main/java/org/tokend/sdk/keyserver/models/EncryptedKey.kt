package org.tokend.sdk.keyserver.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64

open class EncryptedKey(
        @SerializedName("account_id")
        var accountId: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("salt")
        var salt: String,
        @SerializedName("keychain_data")
        var encodedKeychainData: String
) {
    val keychainData: KeychainData
        get() = KeychainData.fromJsonString(String(encodedKeychainData.decodeBase64()))
}