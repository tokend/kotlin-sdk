package org.tokend.sdk.api.tfa

import com.google.gson.annotations.SerializedName

class TfaBackend {
    class Attributes(@SerializedName("priority")
                     var priority: Int? = null,
                     @SerializedName("secret")
                     val secret: String? = null,
                     @SerializedName("seed")
                     val seed: String? = null,
                     @SerializedName("account_id")
                     val accountId: String? = null,
                     @SerializedName("keychain_data")
                     val keychainData: String? = null,
                     @SerializedName("salt")
                     val salt: String? = null)

    @SerializedName("type")
    val type: String? = null
    @SerializedName("id")
    val id: Int? = null
    @SerializedName("attributes")
    val attributes: Attributes? = null

    companion object {
        const val TYPE_TOTP = "totp"
        const val TYPE_PASSWORD = "password"
    }
}