package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.factory.JsonApiTools
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeBase64String

/**
 * Holds encrypted data and cipher params for decryption.
 *
 * @param encodedIv cipher init vector encoded using Base64
 * @param encodedCipherText encrypted data encoded using Base64
 */
open class KeychainData
@JvmOverloads
constructor(
    @get:JsonProperty("IV")
    @JsonAlias("iv")
    val encodedIv: String,
    @get:JsonProperty("cipherText")
    val encodedCipherText: String,
    @get:JsonProperty("cipherName")
    val cipherName: String = "aes",
    @get:JsonProperty("modeName")
    val cipherMode: String = "gcm"
) {
    /**
     * Raw bytes of the init vector.
     */
    @get:JsonIgnore
    val iv: ByteArray
        get() = encodedIv.decodeBase64()

    /**
     * Raw bytes of the encrypted data
     */
    @get:JsonIgnore
    val cipherText: ByteArray
        get() = encodedCipherText.decodeBase64()

    /**
     * Serializes data to JSON.
     */
    fun toJson(): String {
        return JsonApiTools.objectMapper.writeValueAsString(this)
    }

    /**
     * Serializes data to JSON and encodes it with Base64.
     */
    fun encode(): String {
        return toJson()
            .toByteArray()
            .encodeBase64String()
    }

    companion object {
        @JvmStatic
        fun fromRaw(iv: ByteArray, cipherText: ByteArray): KeychainData {
            return KeychainData(iv.encodeBase64String(), cipherText.encodeBase64String())
        }

        @JvmStatic
        fun fromJson(rawJson: String): KeychainData {
            return JsonApiTools.objectMapper
                .readValue(rawJson, KeychainData::class.java)
        }

        @JvmStatic
        fun fromEncoded(rawString: String): KeychainData {
            return fromJson(String(rawString.decodeBase64()))
        }
    }
}
