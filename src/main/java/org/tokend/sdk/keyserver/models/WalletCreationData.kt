package org.tokend.sdk.keyserver.models

import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.utils.extentions.encodeBase64String

class WalletCreationData(
    val type: String,
    val walletId: String,
    val email: String,
    val accountId: String,
    val salt: ByteArray,
    val keychainData: KeychainData,
    val extraAttributes: MutableMap<String, Any> = mutableMapOf(),
    val verificationCode: String? = null
) {
    val relationships: MutableMap<String, WalletRelationship> = mutableMapOf()
    val arrayRelationships: MutableMap<String, Collection<WalletRelationship>> = mutableMapOf()

    val encodedSalt: String
        get() = salt.encodeBase64String()

    val encodedKeychainData: String
        get() = keychainData.encode()

    fun toRequestBody() = mapOf(
        "data" to mapOf(
            "id" to walletId,
            "type" to type,

            "attributes" to mapOf(
                "account_id" to accountId,
                "email" to email,
                "salt" to encodedSalt,
                "keychain_data" to encodedKeychainData,
                "verification_code" to verificationCode
            ) + extraAttributes,

            "relationships" to
                    relationships.mapValues { (_, relationship) ->
                        DataEntity(
                            mapOf(
                                "id" to relationship.id,
                                "type" to relationship.type
                            )
                        )
                    } +
                    arrayRelationships.mapValues { (_, relationships) ->
                        DataEntity(relationships.map { relationship ->
                            mapOf(
                                "id" to relationship.id,
                                "type" to relationship.type
                            )
                        })
                    },
        ),

        "included" to
                relationships.values +
                arrayRelationships.values.flatten()
    )

    companion object {
        const val TYPE_DEFAULT = "wallet"
        const val TYPE_RECOVERY = "recovery-wallet"
    }
}