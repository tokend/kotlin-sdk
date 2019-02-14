package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.generated.resources.AccountResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class AccountModelTest {

    @Test
    fun singleAccount() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                accountResponse.toByteArray(),
                AccountResource::class.java
        )

        val account = document.get()

        JsonApiUtil.checkResourceNullability(account)

        Assert.assertFalse(account.hasAttributes())
        Assert.assertTrue(account.balances.isNotEmpty())
        Assert.assertNotNull(account.referrer)
    }

    private val accountResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\",\n" +
            "      \"type\":\"accounts\",\n" +
            "      \"relationships\":{  \n" +
            "         \"referrer\":{  \n" +
            "            \"data\":{  \n" +
            "               \"id\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\",\n" +
            "               \"type\":\"accounts\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"balances\":{  \n" +
            "            \"data\":[  \n" +
            "               {  \n" +
            "                  \"id\":\"BDLXKLLVS6UIQEDYCZKIC5222GGQLCOTHM4BWXVCBDQMJTUWUFUATV2Q\",\n" +
            "                  \"type\":\"balances\"\n" +
            "               }\n" +
            "            ]\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"id\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\",\n" +
            "         \"type\":\"accounts\",\n" +
            "         \"relationships\":{  \n" +
            "            \"referrer\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"id\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"balances\":{  \n" +
            "               \"data\":[  \n" +
            "                  {  \n" +
            "                     \"id\":\"BDLXKLLVS6UIQEDYCZKIC5222GGQLCOTHM4BWXVCBDQMJTUWUFUATV2Q\",\n" +
            "                     \"type\":\"balances\"\n" +
            "                  }\n" +
            "               ]\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}