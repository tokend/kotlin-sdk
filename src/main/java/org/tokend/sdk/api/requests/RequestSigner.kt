package org.tokend.sdk.api.requests

interface RequestSigner {
    fun sing(data: ByteArray): ByteArray
}