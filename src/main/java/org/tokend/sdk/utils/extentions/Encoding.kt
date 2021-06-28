package org.tokend.sdk.utils.extentions

import org.spongycastle.util.encoders.Hex
import org.tokend.wallet.utils.Base64

fun String.decodeBase64(): ByteArray {
    return this.toByteArray(Charsets.UTF_8).decodeBase64()
}

fun ByteArray.decodeBase64(): ByteArray {
    return Base64.decode(this)
}

fun String.decodeHex(): ByteArray {
    return Hex.decode(this)
}

fun ByteArray.decodeHex(): ByteArray {
    return Hex.decode(this)
}

fun ByteArray.encodeBase64(): ByteArray {
    return Base64.encode(this)
}

fun ByteArray.encodeBase64String(): String {
    return String(Base64.encode(this), Charsets.UTF_8)
}

fun ByteArray.encodeHex(): ByteArray {
    return Hex.encode(this)
}

fun ByteArray.encodeHexString(): String {
    return String(Hex.encode(this), Charsets.UTF_8)
}
