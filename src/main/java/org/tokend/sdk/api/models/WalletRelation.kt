package org.tokend.sdk.api.models

data class WalletRelation(val name: String,
                          private val type: String,
                          private val id: String?,
                          private val accountId: String? = null,
                          private val encryptedWallet: EncryptedWallet? = null) {

    val walletData: WalletData
        get() {
            val walletData = WalletData(
                    type = type,
                    id = id,
                    attributes = null,
                    relationships = hashMapOf()
            )

            if (accountId != null && encryptedWallet != null) {
                val attributes = WalletData.WalletAttributes(
                        accountId = accountId,
                        keychainDataString = encryptedWallet.keychainData,
                        salt = encryptedWallet.salt,
                        email = "",
                        isVerified = true
                )
                walletData.attributes = attributes
            }

            return walletData
        }

    constructor(name: String, type: String, accountId: String?, encryptedWallet: EncryptedWallet)
            : this(name, type, encryptedWallet.walletIdHex, accountId, encryptedWallet)

    companion object {
        var RELATION_RECOVERY = "recovery"
        var RELATION_PASSWORD_FACTOR = "factor"
        var RELATION_PASSWORD = "password"
        var RELATION_KDF = "kdf"
        var RELATION_REFERRER = "referrer"
    }
}
