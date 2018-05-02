package org.tokend.sdk.api.requests

interface RequestSigner {
    /**
     * Returns Base64 encoded signature of given data.
     */
    fun signToBase64(data: ByteArray): String
}