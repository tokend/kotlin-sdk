package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class WalletData(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("attributes")
    val attributes: Attributes,
) {
    class Attributes(
        @JsonProperty("account_id")
        val accountId: String,
        @JsonProperty("email")
        val email: String,
        @JsonProperty("keychain_data")
        val encodedKeychainData: String,
        @JsonProperty("verified")
        val isVerified: Boolean,
    )

    class Session(
        @JsonProperty("id")
        val id: String,
        @JsonProperty("attributes")
        val attributes: Attributes,
    ) {
        class Attributes(
            @JsonProperty("encryption_key")
            val encryptionKey: String,
            @JsonProperty("expires_at")
            val expiresAt: Date
        )
    }
}