package org.tokend.sdk.keyserver.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.tokend.wallet.Transaction

open class WalletRelation(
    @JsonProperty("id")
    val id: String,
    @JsonProperty("type")
    val type: String,
    @JsonProperty("attributes")
    val attributes: Any?
) {
    companion object {
        @JvmStatic
        fun password(encryptedPasswordAccount: EncryptedWalletAccount) = WalletRelation(
            id = "password_id",
            type = "password",
            attributes = encryptedPasswordAccount
        )

        @JvmStatic
        fun kdf(kdfVersion: Long) = WalletRelation(
            id = kdfVersion.toString(),
            type = "kdf",
            attributes = null
        )

        @JvmStatic
        fun transaction(transaction: Transaction) = WalletRelation(
            id = "tx_id",
            type = "transaction",
            attributes = mapOf(
                "envelope" to transaction.getEnvelope().toBase64()
            )
        )

        @JvmStatic
        fun signer(signerData: SignerData) = WalletRelation(
            id = signerData.id,
            type = "signer",
            attributes = signerData
        )
    }
}