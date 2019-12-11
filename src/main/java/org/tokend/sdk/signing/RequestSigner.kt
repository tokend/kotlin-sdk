package org.tokend.sdk.signing

interface RequestSigner {
    /**
     * Current signer's account ID.
     */
    val accountId: String

    /**
     * Current signer's public key
     */
    val keyId: String

    /**
     * Returns Base64 encoded signature of given data.
     */
    fun signToBase64(data: ByteArray): String
}