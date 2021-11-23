package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeBase64String

data class LoginParams(
    @JsonProperty("type")
    val type: String,
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("attributes")
    val kdfAttributes: KdfAttributes
)

data class KdfAttributes(
    @JsonProperty("algorithm")
    val algorithm: String,
    @JsonProperty("bits")
    val bits: Int,
    @JsonProperty("n")
    val n: Int,
    @JsonProperty("p")
    val p: Int,
    @JsonProperty("r")
    val r: Int,
    @JsonProperty("salt")
    var encodedSalt: String?
) {
    constructor(
        algorithm: String,
        bits: Int,
        n: Int,
        p: Int,
        r: Int,
        salt: ByteArray?
    ) : this(algorithm, bits, n, p, r, salt?.encodeBase64String())

    @get:JsonIgnore
    var salt: ByteArray?
        get() = encodedSalt?.decodeBase64()
        set(value) {
            encodedSalt = value?.encodeBase64String()
        }

    @get:JsonIgnore
    val bytes: Int
        get() = bits / 8
}