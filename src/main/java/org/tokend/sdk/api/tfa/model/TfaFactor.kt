package org.tokend.sdk.api.tfa.model

import com.fasterxml.jackson.annotation.JsonProperty

open class TfaFactor(
    @field:JsonProperty("id")
    val id: Long,
    @field:JsonProperty("type")
    val type: Type,
    @field:JsonProperty("attributes")
    val attributes: Attributes
) {
    enum class Type {
        @JsonProperty("totp")
        TOTP,

        @JsonProperty("password")
        PASSWORD,

        @JsonProperty("email")
        EMAIL,

        @JsonProperty("phone")
        PHONE,

        @JsonProperty("telegram")
        TELEGRAM,

        UNKNOWN;

        companion object {
            @JvmStatic
            fun fromLiteral(literal: String?): Type {
                return try {
                    valueOf(literal?.toUpperCase() ?: "")
                } catch (_: Exception) {
                    UNKNOWN
                }
            }
        }
    }

    open class Attributes(
        @JsonProperty("priority")
        val priority: Int
    )

    override fun equals(other: Any?): Boolean {
        return other is TfaFactor
                && other.id == id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}