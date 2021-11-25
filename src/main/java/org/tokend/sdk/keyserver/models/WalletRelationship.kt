package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.sdk.utils.extentions.encodeBase64String
import org.tokend.wallet.Transaction

open class WalletRelationship(
    @get:JsonProperty("id")
    val id: String,
    @get:JsonProperty("type")
    val type: String,
    @get:JsonProperty("attributes")
    val attributes: Any?
) {
    @get:JsonIgnore
    val header: Map<String, String>
        get() = mapOf(
            "id" to id,
            "type" to type
        )

    companion object {
        @JvmStatic
        fun password(
            accountId: String,
            keychainData: KeychainData,
            salt: ByteArray
        ) = WalletRelationship(
            id = "password_id",
            type = "password",
            attributes = mapOf(
                "account_id" to accountId,
                "keychain_data" to keychainData.encode(),
                "salt" to salt.encodeBase64String()
            )
        )

        @JvmStatic
        fun kdf(kdfVersion: Long) = WalletRelationship(
            id = kdfVersion.toString(),
            type = "kdf",
            attributes = null
        )

        @JvmStatic
        fun transaction(transaction: Transaction) = WalletRelationship(
            id = "tx_id",
            type = "transaction",
            attributes = mapOf(
                "envelope" to transaction.getEnvelope().toBase64()
            )
        )

        @JvmStatic
        fun signer(signerData: SignerData) = WalletRelationship(
            id = signerData.id,
            type = "signer",
            attributes = signerData
        )
    }
}