package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.factory.JsonApiToolsProvider
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
    @JsonProperty("IV")
    private val encodedIv: String,
    @JsonProperty("cipherText")
    private val encodedCipherText: String,
    @JsonProperty("cipherName")
    private val cipherName: String = "aes",
    @JsonProperty("modeName")
    private val cipherMode: String = "gcm"
) {
    /**
     * Raw bytes of the init vector.
     */
    val iv: ByteArray
        get() = encodedIv.decodeBase64()

    /**
     * Raw bytes of the encrypted data
     */
    val cipherText: ByteArray
        get() = encodedCipherText.decodeBase64()

    /**
     * Serializes data to JSON and encodes it with Base64
     */
    fun encode(): String {
        return JsonApiToolsProvider.getObjectMapper().writeValueAsString(this)
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
            return JsonApiToolsProvider.getObjectMapper()
                .readValue(rawJson, KeychainData::class.java)
        }

        @JvmStatic
        fun fromEncoded(rawString: String): KeychainData {
            return fromJson(String(rawString.decodeBase64()))
        }
    }
}
