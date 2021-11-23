package org.tokend.sdk.keyserver.models


import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.keyserver.WalletEncryption
import org.tokend.sdk.keyserver.WalletKeyDerivation
import org.tokend.sdk.utils.extentions.decodeBase64

@JvmSuppressWildcards
open class WalletCreationData(
    @JsonProperty("type")
    val type: String,
    @JsonProperty("id")
    val id: String?,
    @JsonProperty("attributes")
    val attributes: WalletAttributes
) {
    open class WalletAttributes(
        @JsonProperty("account_id")
        val accountId: String,
        @JsonProperty("email")
        val email: String,
        @JsonProperty("keychain_data")
        val encodedKeychainData: String,
        @JsonProperty("salt")
        val salt: String,
        @JsonProperty("verified")
        val isVerified: Boolean,
        @JsonProperty("verification_doce")
        val verificationCode: String?,
    ) {
        @get:JsonIgnore
        val keychainData: KeychainData
            get() = KeychainData.fromJson(String(encodedKeychainData.decodeBase64()))
    }

    @JsonProperty("relationships")
    val relationships: MutableMap<String, DataEntity<Any>> = mutableMapOf()

    /**
     * @see WalletKeyDerivation
     * @see WalletEncryption
     * @see WalletCreationData.TYPE_DEFAULT
     * @see WalletCreationData.TYPE_RECOVERY
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

    @JsonIgnore
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
