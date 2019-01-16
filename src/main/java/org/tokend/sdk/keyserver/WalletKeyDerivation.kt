package org.tokend.sdk.keyserver

import org.tokend.kdf.ScryptWithMasterKeyDerivation
import org.tokend.sdk.keyserver.models.KdfAttributes
import org.tokend.sdk.utils.extentions.encodeHexString
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.security.SecureRandom

object WalletKeyDerivation {
    @JvmStatic
    fun deriveWalletEncryptionKey(login: String,
                                  password: CharArray,
                                  kdfAttributes: KdfAttributes): ByteArray {
        val passwordCharBuffer = CharBuffer.wrap(password)
        val passwordByteBuffer = Charset.defaultCharset().encode(passwordCharBuffer)
        passwordCharBuffer.clear()

        val passwordBytes = ByteArray(passwordByteBuffer.remaining())
        passwordByteBuffer.get(passwordBytes).clear()

        val result = deriveKey(login.toByteArray(), passwordBytes,
                WALLET_KEY_MASTER_KEY.toByteArray(), kdfAttributes)
        passwordBytes.fill(0)

        return result
    }

    /**
     * Derives wallet ID for given params
     *
     * @see deriveKey
     */
    @JvmStatic
    fun deriveWalletId(login: String,
                       password: CharArray,
                       kdfAttributes: KdfAttributes): ByteArray {
        val passwordCharBuffer = CharBuffer.wrap(password)
        val passwordByteBuffer = Charset.defaultCharset().encode(passwordCharBuffer)
        passwordCharBuffer.clear()

        val passwordBytes = ByteArray(passwordByteBuffer.remaining())
        passwordByteBuffer.get(passwordBytes).clear()

        val result = deriveKey(login.toByteArray(), passwordBytes,
                WALLET_ID_MASTER_KEY.toByteArray(), kdfAttributes)
        passwordBytes.fill(0)

        return result
    }

    /**
     * Derives wallet ID for given params and encodes it into HEX
     *
     * @see deriveKey
     */
    @JvmStatic
    fun deriveAndEncodeWalletId(login: String,
                                password: CharArray,
                                kdfAttributes: KdfAttributes): String {
        return deriveWalletId(login, password, kdfAttributes).encodeHexString()
    }

    /**
     * Derives wallet key for given params
     *
     * @see ScryptWithMasterKeyDerivation
     * @see <a href="https://tokend.gitlab.io/docs/#wallet-id-derivation">Wallet key derivation docs</a>
     */
    @JvmStatic
    fun deriveKey(login: ByteArray,
                  password: ByteArray,
                  masterKey: ByteArray,
                  kdfAttributes: KdfAttributes): ByteArray {
        val derivation = ScryptWithMasterKeyDerivation(kdfAttributes.n, kdfAttributes.r,
                kdfAttributes.p, login, masterKey)
        val salt = kdfAttributes.salt
                ?: throw IllegalArgumentException("KDF salt is required for derivation")
        return derivation.derive(password, salt, kdfAttributes.bytes)
    }

    /**
     * Generate salt for system KDF params.
     */
    @JvmStatic
    @JvmOverloads
    fun generateKdfSalt(lengthBytes: Int = KDF_SALT_LENGTH_BYTES): ByteArray {
        return SecureRandom().generateSeed(lengthBytes)
    }

    private const val WALLET_ID_MASTER_KEY = "WALLET_ID"
    private const val WALLET_KEY_MASTER_KEY = "WALLET_KEY"
    const val KDF_SALT_LENGTH_BYTES = 16
}