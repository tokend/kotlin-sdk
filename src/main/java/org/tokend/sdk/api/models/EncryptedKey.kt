package org.tokend.sdk.api.models

open class EncryptedKey(
        var accountId: String,
        var email: String,
        var salt: String,
        var keychainData: String
)