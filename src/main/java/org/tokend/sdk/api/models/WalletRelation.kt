package org.tokend.sdk.api.models

/**
 * Created by Oleg Koretsky on 2/8/18.
 */

data class WalletRelation(val name: String,
                          private val type: String,
                          private val id: String?,
                          private val accountId: String? = null,
                          private val encryptedWallet: EncryptedWallet? = null) {

    val walletData: WalletData
        get() {
            val walletData = WalletData()
            walletData.type = type
            id?.let { walletData.id = id }

            if (accountId != null && encryptedWallet != null) {
                val attributes = walletData.WalletAttributes()
                attributes.accountId = accountId
                attributes.setKeychainDataString(encryptedWallet.keychainData!!)
                attributes.salt = encryptedWallet.salt
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
