package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.v3.model.generated.inner.Mask


/**
 * @return true if given [value] is set in bitmask,
 * false otherwise
 */
fun Mask.has(value: Int): Boolean {
    return ((this.value ?: 0) and value) == value
}

/**
 * @see [has]
 */
fun Mask.has(value: Long): Boolean {
    return has(value.toInt())
}