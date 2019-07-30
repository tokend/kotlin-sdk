package org.tokend.sdk.keyserver.models


import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.utils.extentions.decodeBase64
import org.tokend.wallet.Transaction

open class WalletData(
        @SerializedName("type")
        val type: String,
        @SerializedName("id")
        val id: String?,
        @SerializedName("attributes")
        var attributes: WalletAttributes?,
        @SerializedName("relationships")
        val relationships: MutableMap<String, DataEntity<Any>>
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
                encryptedAccount: EncryptedWalletAccount,
                relations: List<WalletRelation>,
                type: String) : this(
            type = type,
            id = walletIdHex,
            attributes = WalletAttributes(
                    accountId = encryptedAccount.accountId,
                    email = encryptedAccount.email,
                    salt = encryptedAccount.encodedSalt,
                    keychainDataString = encryptedAccount.encodedKeychainData,
                    isVerified = false
            ),
            relationships = HashMap()
    ) {
        for (relation in relations) {
            addRelation(relation)
        }
    }

    fun addRelation(relation: WalletRelation) {
        relationships[relation.name] = DataEntity(relation.walletData)
    }

    fun addTransactionRelation(transaction: Transaction) {
        relationships[WalletRelation.RELATION_TRANSACTION] =
                DataEntity(hashMapOf(
                        "id" to "tx",
                        "type" to WalletRelation.RELATION_TRANSACTION,
                        "attributes" to hashMapOf(
                                "envelope" to transaction.getEnvelope().toBase64()
                        ))
                )
    }

    companion object {
        const val TYPE_DEFAULT = "wallet"
        const val TYPE_RECOVERY = "recovery-wallet"
    }
}
