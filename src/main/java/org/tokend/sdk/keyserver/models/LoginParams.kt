package org.tokend.sdk.keyserver.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeBase64String

data class LoginParams(
        @SerializedName("type")
        val type: String,
        @SerializedName("id")
        val id: Long,
        @SerializedName("attributes")
        val kdfAttributes: KdfAttributes
)

data class KdfAttributes(
        @SerializedName("algorithm")
        val algorithm: String,
        @SerializedName("bits")
        val bits: Int,
        @SerializedName("n")
        val n: Int,
        @SerializedName("p")
        val p: Int,
        @SerializedName("r")
        val r: Int,
        @SerializedName("salt")
        var encodedSalt: String?
) {
    var salt: ByteArray?
        get() = encodedSalt?.decodeBase64()
        set(value) {
            encodedSalt = value?.encodeBase64String()
        }

    val bytes: Int
        get() = bits / 8
}