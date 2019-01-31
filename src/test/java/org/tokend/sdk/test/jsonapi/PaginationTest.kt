package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.base.model.DataPage
import org.tokend.sdk.api.v2.accounts.model.AccountResource
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

        val document = JsonApiToolsProvider().getResourceConverter().readDocumentCollection(
                response.toByteArray(),
                AccountResource::class.java
        )

        Assert.assertNotNull(document.links)

        val dataPage = DataPage.fromPageDocument(document)

        Assert.assertEquals("Parsed next cursor is invalid",
                (page + 1).toString(), dataPage.nextCursor)
        Assert.assertTrue("Last page detection failed", dataPage.isLast)
    }
}