package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.inner.Mask


/**
 * @return true if given [value] is set in bitmask,
 * false otherwise
 */
fun Mask.has(value: Int): Boolean {
    return (this.value and value) == value
}

/**
 * @see [has]
 */
fun Mask.has(value: Long): Boolean {
    return has(value.toInt())
}