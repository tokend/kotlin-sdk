package org.tokend.sdk.signing

interface RequestSigner {
    /**
     * Contains current signer's account ID.
     */
    val accountId: String

    /**
     * Contains main (original) account ID (not signer account ID).
     */
    val originalAccountId: String

    /**
     * Returns Base64 encoded signature of given data.
     */
    fun signToBase64(data: ByteArray): String
}