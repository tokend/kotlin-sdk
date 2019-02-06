package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.fees.model.ExactFeeResource
import org.tokend.sdk.api.v2.fees.model.FeeResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class FeesModelTest {
    @Test
    fun feesList() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocumentCollection(
                feesListResponse.toByteArray(),
                FeeResource::class.java
        )

        Assert.assertNotNull(document.meta)
        Assert.assertNotNull(document.links)

        val fees = document.get()

        JsonApiUtil.checkResourceNullability(fees)

        Assert.assertTrue(fees.isNotEmpty())

        fees.forEach { fee ->
            Assert.assertTrue(fee.hasAttributes())
            Assert.assertEquals(fee.appliedTo.accountId, fee.account?.id)
            Assert.assertNotNull(fee.appliedTo)
        }
    }

    @Test
    fun exactFee() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                exactFeeResponse.toByteArray(),
                ExactFeeResource::class.java
        )

        val fee = document.get()

        JsonApiUtil.checkResourceNullability(fee)

        Assert.assertTrue(fee.hasAttributes())
    }

    private val feesListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/fees?page=3\",\n" +
            "      \"first\":\"https://api.com/fees?page=1\",\n" +
            "      \"prev\":\"https://api.com/fees?page=2\",\n" +
            "      \"next\":\"https://api.com/fees?page=4\",\n" +
            "      \"last\":\"https://api.com/fees?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"fees\",\n" +
            "         \"id\":\"eut123Mnwqe\",\n" +
            "         \"attributes\":{  \n" +
            "            \"fixed\":\"0.000000\",\n" +
            "            \"percent\":\"1.000000\",\n" +
            "            \"fee_asset\":\"USD\",\n" +
            "            \"applied_to\":{  \n" +
            "               \"asset\":\"BTC\",\n" +
            "               \"subtype\":0,\n" +
            "               \"fee_type\":4,\n" +
            "               \"account_id\":\"\",\n" +
            "               \"account_type\":-1,\n" +
            "               \"lower_bound\":\"0.000000\",\n" +
            "               \"upper_bound\":\"9223372036854.775807\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"account\":null,\n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"fee_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"fees\",\n" +
            "         \"id\":\"vta1q41iOwbqn\",\n" +
            "         \"attributes\":{  \n" +
            "            \"fixed\":\"0.230000\",\n" +
            "            \"percent\":\"2.104000\",\n" +
            "            \"fee_asset\":\"USD\",\n" +
            "            \"applied_to\":{  \n" +
            "               \"asset\":\"DASH\",\n" +
            "               \"subtype\":1,\n" +
            "               \"fee_type\":3,\n" +
            "               \"account_id\":\"GATXUNHVONTSYADNL3SHNFRSJDD7YQQESFTHULLBBH46O3P4CJFEGLA6\",\n" +
            "               \"account_type\":-1,\n" +
            "               \"lower_bound\":\"150.000000\",\n" +
            "               \"upper_bound\":\"500.000000\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"account\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"id\":\"GATXUNHVONTSYADNL3SHNFRSJDD7YQQESFTHULLBBH46O3P4CJFEGLA6\",\n" +
            "                  \"type\":\"accounts\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"DASH\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"fee_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"USD\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"

    private val exactFeeResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"exact_fees\",\n" +
            "      \"id\":\"vta1q41iOwbqn\",\n" +
            "      \"attributes\":{  \n" +
            "         \"fixed_fee\":\"1.023212\",\n" +
            "         \"percent_fee\":\"2.943122\",\n" +
            "         \"fee_asset\":\"USD\"\n" +
            "      }\n" +
            "   }\n" +
            "}"
}