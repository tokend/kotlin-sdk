package org.tokend.sdk.utils.extentions

/**
 * @return [or] bitmask for given collection
 */
fun Collection<Long>.bitmask(): Long = fold(0L) { acc, i -> acc or i }