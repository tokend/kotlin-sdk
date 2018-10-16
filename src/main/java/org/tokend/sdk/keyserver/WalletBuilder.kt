package org.tokend.sdk.keyserver

import org.tokend.sdk.keyserver.models.KdfAttributes
import org.tokend.sdk.keyserver.models.WalletData
import org.tokend.sdk.keyserver.models.WalletRelation
import java.security.SecureRandom

/**
 * Builder of TokenD wallet.
 *
 * @param email user's email
 * @param password user's password
 * @param secretSeed seed of the keypair
 * @param originalAccountId account ID i.e. public key of the keypair
 * @param kdfAttributes system KDF attributes.
 * For password change or recovery use existing
 * @param kdfVersion system KDF version.
 * For password change or recovery use existing
 */
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

    /**
     * Adds relation for password TFA factor.
     * To increase security you have to use separate keypair for this relation.
     *
     * @param factorSeed seed of the keypair
     * @param factorAccountId account ID i.e. public key of the keypair
     */
    fun addPasswordFactorRelation(factorSeed: CharArray,
                                  factorAccountId: String): WalletBuilder {
        this.passwordFactorSeed = factorSeed
        this.passwordFactorAccount = factorAccountId

        return this
    }

    /**
     * Adds wallet recovery relation.
     * You have to use separate keypair for this relation.
     * Recovery seed is the only way to recover access to the account
     * in case of password loss so it has to be saved by user.
     *
     * @param recoverySeed seed of recovery keypair
     * @param recoveryAccountId account ID i.e. public key of recovery keypair
     */
    fun addRecoveryRelation(recoverySeed: CharArray,
                            recoveryAccountId: String): WalletBuilder {
        this.recoverySeed = recoverySeed
        this.recoveryAccount = recoveryAccountId

        return this
    }

    /**
     * @return result wallet data
     */
    fun build(): WalletData {
        val walletKey = KeyStorage.getWalletKey(email, password, kdfAttributes)
        val walletId = KeyStorage.getWalletIdHex(email, password, kdfAttributes)

        val kdfSalt = kdfAttributes.salt ?: generateKdfSalt()

        val encryptedSeed = KeyStorage.encryptWalletAccount(
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
                    KeyStorage.encryptWalletAccount(
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
                    KeyStorage.encryptWalletAccount(
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

    companion object {
        const val KDF_SALT_LENGTH_BYTES = 16

        @JvmStatic
        fun generateKdfSalt(): ByteArray {
            return SecureRandom().generateSeed(KDF_SALT_LENGTH_BYTES)
        }
    }
}