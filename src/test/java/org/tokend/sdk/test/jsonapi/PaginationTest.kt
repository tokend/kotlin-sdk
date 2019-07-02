package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.JSONAPIDocument
import com.github.jasminb.jsonapi.Link
import com.github.jasminb.jsonapi.Links
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.generated.resources.AccountResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class PaginationTest {
    @Test
    fun dataPage() {
        val limit = 5
        val page = 5

        val response = "{  \n" +
                "   \"data\":[  \n" +
                "      {  \n" +
                "         \"type\":\"accounts\",\n" +
                "         \"id\":\"GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB\"\n" +
                "      },\n" +
                "      {  \n" +
                "         \"type\":\"accounts\",\n" +
                "         \"id\":\"GBFSW4ZSDLNXUKS77LGMQVDNJUCU77ZUZX7AOIWLDF7OPX5F7OPFR67O\"\n" +
                "      }\n" +
                "   ],\n" +
                "   \"links\":{  \n" +
                "      \"next\":\"/v2/assets?include=owner&page[number]=${page + 1}&page[limit]=$limit\",\n" +
                "      \"self\":\"/v2/assets?include=owner&page[number]=$page&page[limit]=$limit\"\n" +
                "   }\n" +
                "}"

        val document = JsonApiToolsProvider.getResourceConverter().readDocumentCollection(
                response.toByteArray(),
                AccountResource::class.java
        )

        Assert.assertNotNull(document.links)

        val dataPage = DataPage.fromPageDocument(document)

        Assert.assertEquals("Parsed next cursor is invalid",
                (page + 1).toString(), dataPage.nextCursor)
        Assert.assertTrue("Last page detection failed", dataPage.isLast)
    }

    @Test
    fun invalidLinks() {
        val document = JSONAPIDocument<List<Int>>(
                listOf(1, 2, 3, 4),
                Links(mapOf(
                        "self" to Link("mocked"),
                        "next" to Link("mocked")
                )),
                emptyMap()
        )

        val page = DataPage.fromPageDocument(document)

        Assert.assertTrue("DataPage must be last if links are invalid " +
                "to avoid infinite pagination", page.isLast)
    }

    @Test
    fun emptyLinks() {
        val document = JSONAPIDocument<List<Int>>(
                listOf(1, 2, 3, 4),
                Links(mapOf(
                        "self" to Link(""),
                        "next" to Link("")
                )),
                emptyMap()
        )

        val page = DataPage.fromPageDocument(document)

        Assert.assertTrue("DataPage must be last if links are empty " +
                "to avoid infinite pagination", page.isLast)
    }

    @Test
    fun noLinks() {
        val document = JSONAPIDocument<List<Int>>(
                listOf(1, 2, 3, 4),
                Links(),
                emptyMap()
        )

        try {
            DataPage.fromPageDocument(document)
        } catch (e: IllegalStateException) {
            // Ok.
            return
        }

        Assert.fail("DataPage must throw exception if document has no links")
    }
}