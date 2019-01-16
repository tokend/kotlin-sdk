package org.tokend.sdk.keyserver.models

import com.google.gson.annotations.SerializedName
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.sdk.utils.extentions.encodeBase64String

/**
 * Holds encrypted wallet account data
 */
open class EncryptedWalletAccount(
        @SerializedName("account_id")
        var accountId: String,
        @SerializedName("email")
        var email: String,
        /**
         * Base64 encoded KDF salt
         */
        @SerializedName("salt")
        var encodedSalt: String,
        /**
         * Base64 encoded [KeychainData]
         */
        @SerializedName("keychain_data")
        var encodedKeychainData: String
) {
    constructor(accountId: String,
                email: String,
                salt: ByteArray,
                keychainData: KeychainData
    ) : this(accountId, email, salt.encodeBase64String(), keychainData.encode())

    val keychainData: KeychainData
        get() = KeychainData.fromEncoded(String(encodedKeychainData.decodeBase64()))
}