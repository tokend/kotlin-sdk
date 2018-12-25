package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.api.v2.keyvalue.model.KeyValueEntryResource

class KeyValueEntriesModelTest {
    @Test
    fun keyValueEntry() {
        val converter = ResourceConverter(
                KeyValueEntryResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                Data.keyValueEntryResponse.toByteArray(),
                KeyValueEntryResource::class.java
        )

        val entry = document.get()

        Assert.assertTrue(entry.hasAttributes())
        Assert.assertEquals(entry.valueTypeI, entry.valueType.value)
        Assert.assertNotNull(entry.value)
    }

    @Test
    fun keyValueEntriesList() {
        val converter = ResourceConverter(
                KeyValueEntryResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.keyValueEntriesListResponse.toByteArray(),
                KeyValueEntryResource::class.java
        )

        Assert.assertNotNull(document.meta)
        Assert.assertNotNull(document.links)

        val entries = document.get()

        Assert.assertTrue(entries.isNotEmpty())

        val entry = entries.first()

        Assert.assertTrue(entry.hasAttributes())
        Assert.assertEquals(entry.valueTypeI, entry.valueType.value)
        Assert.assertNotNull(entry.value)
    }
}