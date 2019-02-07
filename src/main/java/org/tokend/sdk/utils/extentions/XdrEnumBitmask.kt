package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.inner.XdrEnumBitmask


/**
 * @return true if given [value] is set in bitmask,
 * false otherwise
 */
fun XdrEnumBitmask.have(value: Int): Boolean {
    return (mask and value) == value
}

/**
 * @see [have]
 */
fun XdrEnumBitmask.have(value: Long): Boolean {
    return have(value.toInt())
}