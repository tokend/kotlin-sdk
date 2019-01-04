package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.utils.extentions.bitmask

class BitmaskCollection {
    @Test
    fun bitmaskCollection() {
        val collection = listOf(
                0b00001,
                0b00010,
                0b00100,
                0b10000
        )

        Assert.assertEquals(0b10111, collection.map { it.toLong() }.bitmask())
    }
}