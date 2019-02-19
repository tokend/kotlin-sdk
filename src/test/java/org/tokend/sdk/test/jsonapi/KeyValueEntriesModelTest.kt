package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.generated.resources.KeyValueEntryResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class KeyValueEntriesModelTest {
    @Test
    fun keyValueEntry() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                keyValueEntryResponse.toByteArray(),
                KeyValueEntryResource::class.java
        )

        val entry = document.get()

        JsonApiUtil.checkResourceNullability(entry)

        Assert.assertTrue(entry.hasAttributes())
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
}