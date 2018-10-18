package org.tokend.sdk.redirects

enum class ClientRedirectType(
        val value: Int
) {
    UNKNOWN(-1),
    EMAIL_VERIFICATION(1);

    companion object {
        @JvmStatic
        fun fromValue(value: Int): ClientRedirectType {
            return values().find { it.value == value } ?: UNKNOWN
        }
    }
}