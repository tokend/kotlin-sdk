package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.sales.model.SaleResource
import org.tokend.sdk.factory.JsonApiFactory

class SaleModelTest {

    @Test
    fun singleSale() {
        val document = JsonApiFactory().getResourceConverter().readDocument(
                saleResponseUnincluded.toByteArray(),
                SaleResource::class.java
        )

        val sale = document.get()

        JsonApiUtil.checkResourceNullability(sale)
        assertTrue(sale.hasAttributes())
        assertFalse(sale.baseAsset.hasAttributes())
        assertFalse(sale.owner.hasAttributes())
        assertFalse(sale.quoteAssets.first().hasAttributes())
        assertTrue(sale.balances.isNotEmpty())
    }

    @Test
    fun salesList() {

        val converter = ResourceConverter(
                JsonApiFactory().getObjectMapper(),
                SaleResource::class.java,
                AccountResource::class.java,
                AssetResource::class.java
        )

        val document = converter.readDocumentCollection(
                salesListResponseUnincluded.toByteArray(),
                SaleResource::class.java
        )

        val sales = document.get()

        JsonApiUtil.checkResourceNullability(sales)

        assertTrue(sales.isNotEmpty())

        val sale = sales.first()

        assertTrue(sale.hasAttributes())
        assertFalse(sale.baseAsset.hasAttributes())
        assertFalse(sale.owner.hasAttributes())
        assertFalse(sale.quoteAssets.first().hasAttributes())
        assertTrue(sale.balances.isNotEmpty())
    }

    private val saleResponseUnincluded = "{\n" +
            "  \"data\": {\n" +
            "    \"type\": \"sales\",\n" +
            "    \"id\": \"2\",\n" +
            "    \"attributes\": {\n" +
            "      \"start_time\": \"2018-06-19T21:00:00Z\",\n" +
            "      \"end_time\": \"2018-06-29T21:00:00Z\",\n" +
            "      \"soft_cap\": \"1.000000\",\n" +
            "      \"hard_cap\": \"2.000000\",\n" +
            "      \"investors_count\": 23,\n" +
            "      \"type\": \"crowd_funding\",\n" +
            "      \"type_i\": 2,\n" +
            "      \"state\": \"closed\", \n" +
            "      \"state_i\": 2,\n" +
            "      \"base_hard_cap\": \"10.000000\",\n" +
            "      \"base_current_cap\": \"0.000000\",\n" +
            "      \"current_cap\": \"0.872974\",\n" +
            "      \"details\": {\n" +
            "         \"description\": \"FAB6BK75TFNXPRULP6YUDYBMFZUKOEYBYNZPNGRHGDMVROVEJVJQ\",\n" +
            "         \"logo\": {\n" +
            "           \"key\": \"dpurah4infpebjhcost7fvnhjxlqgqdft3bamery2an3otqpbx6jbvdt\",\n" +
            "           \"mime_type\": \"image/png\",\n" +
            "           \"name\": \"TokenD - Demo - Images (1).png\"\n" +
            "         },\n" +
            "         \"name\": \"Pre-sale\",\n" +
            "         \"short_description\": \"The best token\",\n" +
            "         \"youtube_video_id\": \"\"\n" +
            "      },\n" +
            "      \"sale_quote_assets\": [\n" +
            "        {\n" +
            "          \"asset\": \"ETH\",\n" +
            "          \"price\": \"0.000383\",\n" +
            "          \"hard_cap\": \"0.008754\",\n" +
            "          \"current_cap\": \"0.003821\",\n" +
            "          \"total_current_cap\": \"0.003822\"\n" +
            "        }\n" +
            "      ],\n" +
            "      \"balances\": {\n" +
            "        \"ETH\": \"BDZJQYMTKEHA3GDTJJSR5XPY22WHIOIRXWJMPZORZCDFD2EPDOJCROMJ\"\n" +
            "      }\n" +
            "    },\n" +
            "    \"relationships\": {\n" +
            "      \"owner\": {\n" +
            "        \"data\": {\n" +
            "          \"type\": \"accounts\",\n" +
            "          \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "        }\n" +
            "      },\n" +
            "       \"default_quote_asset\": {\n" +
            "        \"data\": {\n" +
            "           \"type\": \"assets\",\n" +
            "           \"id\": \"USD\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"base_asset\": {\n" +
            "        \"data\": {\n" +
            "           \"type\": \"assets\",\n" +
            "           \"id\": \"QTK\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"quote_assets\": {\n" +
            "          \"data\": [\n" +
            "               {\n" +
            "                   \"type\": \"assets\",\n" +
            "                   \"id\": \"ETH\"\n" +
            "               }\n" +
            "            ]\n" +
            "       }\n" +
            "    }\n" +
            "  }\n" +
            "}"

    private val salesListResponseUnincluded = "{\n" +
            "  \"meta\": {\n" +
            "    \"current_page\": 3,\n" +
            "    \"total_pages\": 10\n" +
            "  },\n" +
            "  \"links\": {\n" +
            "    \"self\": \"https://api.com/sales?page=3\",\n" +
            "    \"first\": \"https://api.com/sales?page=1\",\n" +
            "    \"prev\": \"https://api.com/sales?page=2\",\n" +
            "    \"next\": \"https://api.com/sales?page=4\",\n" +
            "    \"last\": \"https://api.com/sales?page=15\"\n" +
            "  },\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"type\": \"sales\",\n" +
            "      \"id\": \"2\",\n" +
            "      \"attributes\": {\n" +
            "        \"start_time\": \"2018-06-19T21:00:00Z\",\n" +
            "        \"end_time\": \"2018-06-29T21:00:00Z\",\n" +
            "        \"soft_cap\": \"1.000000\",\n" +
            "        \"hard_cap\": \"2.000000\",\n" +
            "        \"investors_count\": 23,\n" +
            "        \"type\": \"crowd_funding\",\n" +
            "        \"type_i\": 2,\n" +
            "        \"state\": \"closed\",\n" +
            "        \"state_i\": 2,\n" +
            "        \"base_hard_cap\": \"10.000000\",\n" +
            "        \"base_current_cap\": \"0.000000\",\n" +
            "        \"current_cap\": \"0.872974\",\n" +
            "        \"details\": {\n" +
            "           \"description\": \"FAB6BK75TFNXPRULP6YUDYBMFZUKOEYBYNZPNGRHGDMVROVEJVJQ\",\n" +
            "           \"logo\": {\n" +
            "             \"key\": \"dpurah4infpebjhcost7fvnhjxlqgqdft3bamery2an3otqpbx6jbvdt\",\n" +
            "             \"mime_type\": \"image/png\",\n" +
            "             \"name\": \"TokenD - Demo - Images (1).png\"\n" +
            "           },\n" +
            "           \"name\": \"Pre-sale\",\n" +
            "           \"short_description\": \"The best token\",\n" +
            "           \"youtube_video_id\": \"\"\n" +
            "        },\n" +
            "        \"sale_quote_assets\": [\n" +
            "          {\n" +
            "            \"asset\": \"ETH\",\n" +
            "            \"price\": \"0.000383\",\n" +
            "            \"hard_cap\": \"0.008754\",\n" +
            "            \"current_cap\": \"0.003821\",\n" +
            "            \"total_current_cap\": \"0.003822\"\n" +
            "          }\n" +
            "        ],\n" +
            "        \"balances\": {\n" +
            "          \"ETH\": \"BDZJQYMTKEHA3GDTJJSR5XPY22WHIOIRXWJMPZORZCDFD2EPDOJCROMJ\"\n" +
            "        }\n" +
            "      },\n" +
            "      \"relationships\": {\n" +
            "        \"owner\": {\n" +
            "          \"data\": {\n" +
            "            \"type\": \"accounts\",\n" +
            "            \"id\": \"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "          }\n" +
            "        },\n" +
            "       \"default_quote_asset\": {\n" +
            "           \"data\": {\n" +
            "               \"type\": \"assets\",\n" +
            "               \"id\": \"USD\"\n" +
            "           }\n" +
            "       },\n" +
            "       \"base_asset\": {\n" +
            "           \"data\": {\n" +
            "               \"type\": \"assets\",\n" +
            "               \"id\": \"QTK\"\n" +
            "           }\n" +
            "      },\n" +
            "      \"quote_assets\": {\n" +
            "          \"data\": [\n" +
            "               {\n" +
            "                   \"type\": \"assets\",\n" +
            "                   \"id\": \"ETH\"\n" +
            "               }\n" +
            "            ]\n" +
            "       }\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}