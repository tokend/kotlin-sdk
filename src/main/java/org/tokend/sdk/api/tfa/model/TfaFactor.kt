package org.tokend.sdk.api.tfa.model

import com.google.gson.annotations.SerializedName

open class TfaFactor(
        @SerializedName("id")
        val id: Long,
        @SerializedName("type")
        val type: Type,
        @SerializedName("attributes")
        val attributes: Attributes
) {
    enum class Type {
        @SerializedName("totp")
        TOTP,
        @SerializedName("password")
        PASSWORD,
        @SerializedName("email")
        EMAIL,
        @SerializedName("phone")
        PHONE,
        @SerializedName("telegram")
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
            @SerializedName("priority")
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