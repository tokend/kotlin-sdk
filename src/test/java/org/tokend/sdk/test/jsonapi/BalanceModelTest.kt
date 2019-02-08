package org.tokend.sdk.test.jsonapi

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.tokend.sdk.api.generated.resources.BalanceResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class BalanceModelTest {
    @Test
    fun singleBalance() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                balanceResponse.toByteArray(),
                BalanceResource::class.java
        )

        val balance = document.get()

        JsonApiUtil.checkResourceNullability(balance)

        assertTrue(balance.hasAttributes())
        assertFalse(balance.asset.hasAttributes())
    }

    @Test
    fun balanceList() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocumentCollection(
                balanceListResponseUnincluded.toByteArray(),
                BalanceResource::class.java
        )

        val balances = document.get()

        JsonApiUtil.checkResourceNullability(balances)

        assertTrue(balances.isNotEmpty())

        val balance = balances.first()

        assertTrue(balance.hasAttributes())
        assertFalse(balance.asset.hasAttributes())
    }

    private val balanceResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\",\n" +
            "      \"type\":\"balances\",\n" +
            "      \"attributes\":{  \n" +
            "         \"available\":\"0.000000\",\n" +
            "         \"locked\":\"0.000000\",\n" +
            "         \"require_review\":false\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"USD\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"account\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    private val balanceListResponseUnincluded = "{  \n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"id\":\"BD2U4FYCQ6TEVXJZAFP2VB22NKFBLJKKVM625DEM4BXMQN6AOZFTHQAB\",\n" +
            "         \"type\":\"balances\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"USD\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"id\":\"BDA5XOKOHG2IRYSDCU442GQKEPATIZP62QXFVCYR4J37O56WBJVV4OCH\",\n" +
            "         \"type\":\"balances\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"id\":\"BDHTFOKCCWSJKQV3M7YPISAXOSEU34VPN64WJ3FUJJXZDMFYLDDVJKRZ\",\n" +
            "         \"type\":\"balances\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available\":\"0.000000\",\n" +
            "            \"locked\":\"0.000000\",\n" +
            "            \"require_review\":false\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"ETH\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}