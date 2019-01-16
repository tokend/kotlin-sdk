package org.tokend.sdk.tfa

import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.keyserver.WalletEncryption
import org.tokend.sdk.keyserver.WalletKeyDerivation
import org.tokend.sdk.keyserver.models.KdfAttributes
import org.tokend.sdk.keyserver.models.KeychainData
import org.tokend.sdk.utils.extentions.encodeBase64String
import org.tokend.wallet.Account

/**
 * OTP generator for [TfaFactor.Type.PASSWORD] factor type.
 */
class PasswordTfaOtpGenerator {
    fun generate(tfaException: NeedTfaException, email: String, password: CharArray): String {
        val kdfAttributes = KdfAttributes("scrypt",
                256, 4096, 1, 8, tfaException.salt)
        val key = WalletKeyDerivation.deriveWalletEncryptionKey(email, password, kdfAttributes)
        val keychainData = KeychainData.fromEncoded(tfaException.keychainData)
        val seed = try {
            WalletEncryption.decryptSecretSeed(keychainData, key)
        } catch (e: Exception) {
            return ""
        } finally {
            key.fill(0)
        }

        val account = Account.fromSecretSeed(seed)
        seed.fill('0')
        val signature = account.sign(tfaException.token.toByteArray())

        return signature.encodeBase64String()
    }
}