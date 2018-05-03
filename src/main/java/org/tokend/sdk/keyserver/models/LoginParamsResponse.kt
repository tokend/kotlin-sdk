package org.tokend.sdk.keyserver.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64

class LoginParamsResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("attributes")
    val kdfAttributes: KdfAttributes
) {
    override fun toString(): String {
        return "LoginParamsResponse(type=$type, id=$id, kdfAttributes=$kdfAttributes)"
    }
}

class KdfAttributes(
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
    var encodedSalt: String
) {
    val salt: ByteArray
        get() = encodedSalt.decodeBase64()

    override fun toString(): String {
        return "KdfAttributes(algorithm=$algorithm, bits=$bits, n=$n, p=$p, r=$r, encodedSalt=$encodedSalt, salt=${String(salt!!)})"
    }
}
