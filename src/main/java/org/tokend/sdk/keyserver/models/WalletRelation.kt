package org.tokend.sdk.keyserver.models

open class WalletRelation(val name: String,
                          private val type: String,
                          private val id: String?,
                          private val accountId: String? = null,
                          private val encryptedWalletAccount: EncryptedWalletAccount? = null) {

    val walletData: WalletData
        get() {
            val walletData = WalletData(
                    type = type,
                    id = id,
                    attributes = null,
                    relationships = hashMapOf()
            )

            if (accountId != null && encryptedWalletAccount != null) {
                val attributes = WalletData.WalletAttributes(
                        accountId = accountId,
                        keychainDataString = encryptedWalletAccount.encodedKeychainData,
                        salt = encryptedWalletAccount.encodedSalt,
                        email = "",
                        isVerified = true
                )
                walletData.attributes = attributes
            }

            return walletData
        }

    companion object {
        const val RELATION_RECOVERY = "recovery"
        const val RELATION_PASSWORD_FACTOR = "factor"
        const val RELATION_PASSWORD = "password"
        const val RELATION_KDF = "kdf"
        const val RELATION_REFERRER = "referrer"
        const val RELATION_TRANSACTION = "transaction"
    }
}
