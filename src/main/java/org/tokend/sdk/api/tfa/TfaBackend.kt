package org.tokend.sdk.api.tfa

import com.google.gson.annotations.SerializedName

class TfaBackend {
    enum class Type(val literal: String) {
        @SerializedName("totp")
        TOTP("totp"),
        @SerializedName("password")
        PASSWORD("password"),
        UNKNOWN("unknown");

        companion object {
            @JvmStatic
            fun fromLiteral(literal: String?): Type {
                return values().find { it.literal == literal } ?: UNKNOWN
            }
        }
    }

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
    var type: TfaBackend.Type = TfaBackend.Type.UNKNOWN
    @SerializedName("id")
    val id: Int? = null
    @SerializedName("attributes")
    val attributes: Attributes? = null
}