package org.tokend.sdk.redirects

enum class ClientRedirectType(
        val value: Int,
        val literal: String
) {
    UNKNOWN(-1, "unknown"),
    EMAIL_VERIFICATION(1, "api-verify");

    companion object {
        @JvmStatic
        fun fromValue(value: Int): ClientRedirectType {
            return values().find { it.value == value } ?: UNKNOWN
        }

        @JvmStatic
        fun fromName(name: String): ClientRedirectType {
            return values().find { it.literal == name } ?: UNKNOWN
        }
    }
}