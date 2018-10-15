package org.tokend.sdk.keyserver

import org.tokend.sdk.keyserver.models.WalletData
import org.tokend.sdk.keyserver.models.WalletRelation
import org.tokend.sdk.keyserver.models.KdfAttributes

class WalletBuilder(
        val email: String,
        val password: CharArray,
        val secretSeed: CharArray,
        val originalAccountId: String,
        val kdfAttributes: KdfAttributes,
        val kdfVersion: Long
) {
    private var passwordFactorSeed: CharArray? = null
    private var passwordFactorAccount: String? = null

    private var recoverySeed: CharArray? = null
    private var recoveryAccount: String? = null

    fun addPasswordFactorRelation(factorSeed: CharArray,
                                  factorAccountId: String): WalletBuilder {
        this.passwordFactorSeed = factorSeed
        this.passwordFactorAccount = factorAccountId

        return this
    }

    fun addRecoveryRelation(recoverySeed: CharArray,
                            recoveryAccountId: String): WalletBuilder {
        this.recoverySeed = recoverySeed
        this.recoveryAccount = recoveryAccountId

        return this
    }

    fun build(): WalletData {
        val walletKey = KeyStorage.getWalletKey(email, password, kdfAttributes)
        val walletId = KeyStorage.getWalletIdHex(email, password, kdfAttributes)

        val kdfSalt = kdfAttributes.salt

        val encryptedSeed = KeyStorage.encryptWalletKey(
                email,
                secretSeed,
                originalAccountId,
                walletKey,
                kdfSalt
        )

        val wallet = WalletData(walletId, encryptedSeed, listOf())

        wallet.addRelation(
                WalletRelation(
                        WalletRelation.RELATION_KDF,
                        WalletRelation.RELATION_KDF,
                        kdfVersion.toString()
                )
        )

        if (passwordFactorSeed != null && passwordFactorAccount != null) {
            val encryptedPasswordFactor =
                    KeyStorage.encryptWalletKey(
                            email,
                            passwordFactorSeed!!,
                            passwordFactorAccount!!,
                            walletKey,
                            kdfSalt
                    )
            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_PASSWORD_FACTOR,
                            WalletRelation.RELATION_PASSWORD,
                            walletId,
                            passwordFactorAccount!!,
                            encryptedPasswordFactor)
            )
        }

        if (recoverySeed != null && recoveryAccount != null) {
            val recoveryWalletKey = KeyStorage.getWalletKey(email, recoverySeed!!, kdfAttributes)
            val recoveryWalletId = KeyStorage.getWalletIdHex(email, recoverySeed!!, kdfAttributes)

            val encryptedRecovery =
                    KeyStorage.encryptWalletKey(
                            email,
                            recoverySeed!!,
                            recoveryAccount!!,
                            recoveryWalletKey,
                            kdfSalt
                    )

            wallet.addRelation(
                    WalletRelation(
                            WalletRelation.RELATION_RECOVERY,
                            WalletRelation.RELATION_RECOVERY,
                            recoveryWalletId,
                            recoveryAccount!!,
                            encryptedRecovery)
            )
        }

        return wallet
    }

}