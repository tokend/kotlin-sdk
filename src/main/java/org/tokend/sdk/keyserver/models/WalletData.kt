package org.tokend.sdk.keyserver.models


import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.utils.extentions.decodeBase64

open class WalletData(
        @SerializedName("type")
        var type: String,
        @SerializedName("id")
        var id: String?,
        @SerializedName("attributes")
        var attributes: WalletAttributes?,
        @SerializedName("relationships")
        var relationships: MutableMap<String, DataEntity<Any>>
) {
    open class WalletAttributes(
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

    constructor(walletIdHex: String,
                encryptedKey: EncryptedKey,
                relations: List<WalletRelation>) : this(
            type = "wallet",
            id = walletIdHex,
            attributes = WalletAttributes(
                    accountId = encryptedKey.accountId,
                    email = encryptedKey.email,
                    salt = encryptedKey.salt,
                    keychainDataString = encryptedKey.encodedKeychainData,
                    isVerified = false
            ),
            relationships = HashMap()
    ) {
        for (relation in relations) {
            addRelation(relation)
        }
    }

    fun addRelation(relation: WalletRelation) {
        relationships.put(relation.name, DataEntity(relation.walletData))
    }
}
