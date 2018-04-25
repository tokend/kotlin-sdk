package org.tokend.sdk.api.models


import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.utils.extentions.decodeBase64

class WalletData() {
    @SerializedName("type")
    var type: String? = null
    @SerializedName("id")
    var id: String? = null
    @SerializedName("attributes")
    var attributes: WalletAttributes? = null
    @SerializedName("relationships")
    var relationships: MutableMap<String, DataEntity<Any>>? = null

    inner class WalletAttributes {
        @SerializedName("account_id")
        var accountId: String? = null
        @SerializedName("email")
        var email: String? = null
        @SerializedName("keychain_data")
        private var keychainDataString: String? = null
        @SerializedName("salt")
        var salt: String? = null
        @SerializedName("verified")
        var isVerified: Boolean = false

        val keychainData: KeychainData?
            get() = this.keychainDataString?.let {
                KeychainData.fromJson(String(it.decodeBase64()))
            }

        val iv: ByteArray?
            get() = this.keychainData?.iv

        val cipherText: ByteArray?
            get() = this.keychainData?.cipherText

        fun setKeychainDataString(keychainDataString: String) {
            this.keychainDataString = keychainDataString
        }
    }

    constructor(encryptedWallet: EncryptedWallet, accountId: String,
                relations: List<WalletRelation>) : this() {
        id = encryptedWallet.walletIdHex
        type = "wallet"
        attributes = WalletAttributes()
        attributes!!.accountId = accountId
        attributes!!.email = encryptedWallet.username
        attributes!!.salt = encryptedWallet.salt
        attributes!!.setKeychainDataString(encryptedWallet.keychainData!!)

        relationships = HashMap()
        for (relation in relations) {
            relationships?.put(relation.name, DataEntity(relation.walletData))
        }
    }
}
