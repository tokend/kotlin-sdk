package org.tokend.sdk.signing

interface RequestSigner {
    /**
     * Contains current signer's account ID.
     */
    val accountId: String

    /**
     * Returns Base64 encoded signature of given data.
     */
    fun signToBase64(data: ByteArray): String
}