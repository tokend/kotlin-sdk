package org.tokend.sdk.test

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.MappedCallableApiRequest
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.utils.SimplePagedResourceLoader

class PagedResourceLoader {
    @Test
    fun local() {
        val data = (0..1000).toList()
        val limit = 60

        val getPage = { cursor: String? ->
            val cursorInt = cursor?.toIntOrNull() ?: 0
            val start = cursorInt * limit
            val end = Math.min(start + limit, data.size)

            MappedCallableApiRequest(
                    {
                        DataPage(
                                nextCursor = cursorInt.inc().toString(),
                                items = data.subList(start, end),
                                isLast = end == data.size
                        )
                    },
                    { it }
            )
        }

        val loader = SimplePagedResourceLoader(getPage)

        val loaded = loader.loadAll().execute().get()

        Assert.assertArrayEquals(data.toIntArray(), loaded.toIntArray())
    }
}