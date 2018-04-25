package org.tokend.sdk.utils.extentions

import org.spongycastle.util.encoders.Base64
import org.spongycastle.util.encoders.Hex
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

fun String.decodeBase64(): ByteArray {
    return Base64.decode(this)
}

fun String.decodeHex(): ByteArray {
    return Hex.decode(this)
}

fun ByteArray.encodeBase64(): ByteArray {
    return Base64.encode(this)
}

fun ByteArray.encodeBase64String(): String {
    return String(Base64.encode(this))
}

fun ByteArray.decodeBase64(): ByteArray {
    return Base64.decode(this)
}

/**
 * Returns SHA-256 hash of `data`.
 * @param data
 */
fun ByteArray.hash(): ByteArray {
    return try {
        val md = MessageDigest.getInstance("SHA-256")
        md.update(this)
        md.digest()
    } catch (e: NoSuchAlgorithmException) {
        throw RuntimeException("SHA-256 not implemented")
    }
}

fun ByteArray.encodeHex(): ByteArray {
    return Hex.encode(this)
}

fun ByteArray.encodeHexString(): String {
    return String(Hex.encode(this))
}

fun ByteArray.decodeHex(): ByteArray {
    return Hex.decode(this)
}