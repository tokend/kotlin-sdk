package org.tokend.sdk.utils

object HashCodes {
    /**
     * Allow to create combined hash codes of given items.
     */
    fun ofMany(vararg items: Any?): Int {
        return if (items.isEmpty()) {
            0
        } else {
            var res = 1

            for (i in 0 until items.size) {
                res = 31 * res + (items[i]?.hashCode() ?: 0)
            }

            res
        }
    }
}