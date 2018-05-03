package org.tokend.sdk.api.models

data class WalletRelation(val name: String,
                          private val type: String,
                          private val id: String?,
                          private val accountId: String? = null,
                          private val encryptedKey: EncryptedKey? = null) {

    val walletData: WalletData
        get() {
            val walletData = WalletData(
                    type = type,
                    id = id,
                    attributes = null,
                    relationships = hashMapOf()
            )

            if (accountId != null && encryptedKey != null) {
                val attributes = WalletData.WalletAttributes(
                        accountId = accountId,
                        keychainDataString = encryptedKey.keychainData,
                        salt = encryptedKey.salt,
                        email = "",
                        isVerified = true
                )
                walletData.attributes = attributes
            }

            return walletData
        }

    companion object {
        var RELATION_RECOVERY = "recovery"
        var RELATION_PASSWORD_FACTOR = "factor"
        var RELATION_PASSWORD = "password"
        var RELATION_KDF = "kdf"
        var RELATION_REFERRER = "referrer"
    }
}
