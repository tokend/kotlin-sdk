package org.tokend.sdk.keyserver.models


import com.google.gson.annotations.SerializedName
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.keyserver.WalletEncryption
import org.tokend.sdk.keyserver.WalletKeyDerivation
import org.tokend.sdk.utils.extentions.decodeBase64

@JvmSuppressWildcards
open class WalletData(
        @SerializedName("type")
        val type: String,
        @SerializedName("id")
        val id: String?,
        @SerializedName("attributes")
        val attributes: WalletAttributes
) {
    open class WalletAttributes(
            @SerializedName("account_id")
            val accountId: String,
            @SerializedName("email")
            val email: String,
            @SerializedName("keychain_data")
            val encodedKeychainData: String,
            @SerializedName("salt")
            val salt: String,
            @SerializedName("verified")
            val isVerified: Boolean,
            @SerializedName("verification_doce")
            val verificationCode: String?,
    ) {
        val keychainData: KeychainData
            get() = KeychainData.fromJson(String(encodedKeychainData.decodeBase64()))
    }

    @SerializedName("relationships")
    val relationships: MutableMap<String, DataEntity<Any>> = mutableMapOf()

    /**
     * @see WalletKeyDerivation
     * @see WalletEncryption
     * @see WalletData.TYPE_DEFAULT
     * @see WalletData.TYPE_RECOVERY
     */
    constructor(
            type: String,
            walletIdHex: String,
            encryptedAccount: EncryptedWalletAccount,
            verificationCode: String? = null,
    ) : this(
            type = type,
            id = walletIdHex,
            attributes = WalletAttributes(
                    accountId = encryptedAccount.accountId,
                    email = encryptedAccount.email,
                    salt = encryptedAccount.encodedSalt,
                    encodedKeychainData = encryptedAccount.encodedKeychainData,
                    isVerified = false,
                    verificationCode = verificationCode,
            )
    )

    fun addRelation(name: String, relation: WalletRelation) {
        relationships[name] = DataEntity(relation)
    }

    fun addArrayRelation(name: String, relations: Collection<WalletRelation>) {
        relationships[name] = DataEntity(relations)
    }

    fun getFlattenRelationships(): List<WalletRelation> =
            relationships
                    .values
                    .flatMap { relation ->
                        val data = relation.data
                        if (data is Collection<*>)
                            data.map { it as WalletRelation }
                        else
                            listOf(data as WalletRelation)

                    }

    companion object {
        const val TYPE_DEFAULT = "wallet"
        const val TYPE_RECOVERY = "recovery-wallet"
    }
}
