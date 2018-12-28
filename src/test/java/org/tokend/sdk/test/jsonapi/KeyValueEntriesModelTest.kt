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
                keyValueEntryResponse.toByteArray(),
                KeyValueEntryResource::class.java
        )

        val entry = document.get()

        JsonApiUtil.checkResourceNullability(entry)

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
                keyValueEntriesListResponse.toByteArray(),
                KeyValueEntryResource::class.java
        )

        Assert.assertNotNull(document.meta)
        Assert.assertNotNull(document.links)

        val entries = document.get()

        JsonApiUtil.checkResourceNullability(entries)

        Assert.assertTrue(entries.isNotEmpty())

        val entry = entries.first()

        Assert.assertTrue(entry.hasAttributes())
        Assert.assertEquals(entry.valueTypeI, entry.valueType.value)
        Assert.assertNotNull(entry.value)
    }

    private val keyValueEntryResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"key_value_entries\",\n" +
            "      \"id\":\"issuance_tasks:ETH\",\n" +
            "      \"attributes\":{  \n" +
            "         \"value_type\":\"uint32\",\n" +
            "         \"value_type_i\":1,\n" +
            "         \"value\":{  \n" +
            "            \"uint32\":8,\n" +
            "            \"uint64\":null,\n" +
            "            \"string\":null\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    private val keyValueEntriesListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/key_value?page=3\",\n" +
            "      \"first\":\"https://api.com/key_value?page=1\",\n" +
            "      \"prev\":\"https://api.com/key_value?page=2\",\n" +
            "      \"next\":\"https://api.com/key_value?page=4\",\n" +
            "      \"last\":\"https://api.com/key_value?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"key_value_entries\",\n" +
            "         \"id\":\"issuance_tasks:ETH\",\n" +
            "         \"attributes\":{  \n" +
            "            \"value_type\":\"uint32\",\n" +
            "            \"value_type_i\":1,\n" +
            "            \"value\":{  \n" +
            "               \"uint32\":8,\n" +
            "               \"uint64\":null,\n" +
            "               \"string\":null\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}