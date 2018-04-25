package org.tokend.sdk.keyserver.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64

class LoginParamsResponse {
    @SerializedName("type")
    var type: String? = null
    @SerializedName("id")
    var id: Long? = null
    @SerializedName("attributes")
    var kdfAttributes: KdfAttributes? = null

    override fun toString(): String {
        return "LoginParamsResponse(type=$type, id=$id, kdfAttributes=$kdfAttributes)"
    }
}

class KdfAttributes() {
    @SerializedName("algorithm")
    @Expose
    var algorithm: String? = null
    @SerializedName("bits")
    @Expose
    var bits: Int? = null
    @SerializedName("n")
    @Expose
    var n: Int? = null
    @SerializedName("p")
    @Expose
    var p: Int? = null
    @SerializedName("r")
    @Expose
    var r: Int? = null
    @SerializedName("salt")
    @Expose
    var encodedSalt: String? = null

    val salt: ByteArray?
        get() = encodedSalt?.decodeBase64()

    override fun toString(): String {
        return "KdfAttributes(algorithm=$algorithm, bits=$bits, n=$n, p=$p, r=$r, encodedSalt=$encodedSalt, salt=${String(salt!!)})"
    }
}
