package org.tokend.sdk.utils.extentions

import org.tokend.sdk.api.generated.inner.XdrEnumBitmask


/**
 * @return true if given [value] is set in bitmask,
 * false otherwise
 */
fun XdrEnumBitmask.has(value: Int): Boolean = has(value.toLong())

/**
 * @see [has]
 */
fun XdrEnumBitmask.has(value: Long): Boolean {
    return has(value.toInt())
}