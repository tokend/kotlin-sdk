package org.tokend.sdk.api.models


import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.utils.extentions.decodeBase64

class WalletData(
        @SerializedName("type")
        var type: String,
        @SerializedName("id")
        var id: String?,
        @SerializedName("attributes")
        var attributes: WalletAttributes?,
        @SerializedName("relationships")
        var relationships: MutableMap<String, DataEntity<Any>>
) {
    class WalletAttributes(
            @SerializedName("account_id")
            var accountId: String,
            @SerializedName("email")
            var email: String,
            @SerializedName("keychain_data")
            private var keychainDataString: String,
            @SerializedName("salt")
            var salt: String,
            @SerializedName("verified")
            var isVerified: Boolean) {

        val keychainData: KeychainData
            get() = KeychainData.fromJson(String(keychainDataString.decodeBase64()))

        val iv: ByteArray?
            get() = this.keychainData.iv

        val cipherText: ByteArray?
            get() = this.keychainData.cipherText

        fun setKeychainDataString(keychainDataString: String) {
            this.keychainDataString = keychainDataString
        }
    }

    constructor(encryptedWallet: EncryptedWallet, accountId: String,
                relations: List<WalletRelation>) : this(
            type = "wallet",
            id = encryptedWallet.walletIdHex,
            attributes = WalletAttributes(
                    accountId = accountId,
                    email = encryptedWallet.username,
                    salt = encryptedWallet.salt,
                    isVerified = false,
                    keychainDataString = ""
            ),
            relationships = HashMap()
    ) {
        id = encryptedWallet.walletIdHex
        type = "wallet"
        attributes?.setKeychainDataString(encryptedWallet.keychainData)

        for (relation in relations) {
            relationships.put(relation.name, DataEntity(relation.walletData))
        }
    }
}
