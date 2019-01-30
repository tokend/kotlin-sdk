package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.offers.model.OfferResource
import org.tokend.sdk.factory.JsonApiFactory

class OffersModelTest {
    @Test
    fun singleOffer() {
        val document = JsonApiFactory().getResourceConverter().readDocument(
                offerResponseUnincluded.toByteArray(),
                OfferResource::class.java
        )

        val offer = document.get()

        JsonApiUtil.checkResourceNullability(offer)

        Assert.assertTrue(offer.hasAttributes())
        Assert.assertNotNull(offer.baseAsset)
        Assert.assertNotNull(offer.quoteAsset)
    }

    private val offerResponseUnincluded = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"offers\",\n" +
            "      \"id\":\"10\",\n" +
            "      \"attributes\":{  \n" +
            "         \"order_book_id\":0,\n" +
            "         \"fee\":{\n" +
                "        \"fixed\": \"0.000000\",\n" +
                "        \"calculated_percent\": \"0.000000\"\n" +
                "      },\n" +
            "         \"base_asset_code\":\"RTOKEN\",\n" +
            "         \"quote_asset_code\":\"BTC\",\n" +
            "         \"is_buy\":true,\n" +
            "         \"base_amount\":\"1.000000\",\n" +
            "         \"quote_amount\":\"9.000000\",\n" +
            "         \"price\":\"9.000000\",\n" +
            "         \"created_at\":\"2018-09-04T16:10:42Z\"\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"base_balance\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"balances\",\n" +
            "               \"id\":\"BDAS3BZ3CWUB56I2IVCEK3MUWGMLRSXPAEM634YHZWGKSGD7RNSZDIJT\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"quote_balance\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"balances\",\n" +
            "               \"id\":\"BCGYBQEWG3MTWFBHVPHOYTXXKWK6PW6IF3QACB7QW2KIUBU3UIJMAYXI\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"owner\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GAULFBQKQTFHHHZEIRMYVCGTY47FKWEW7P2BY2YT45HAEODAIJUJH23T\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"base_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"RTOKEN\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"quote_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"BTC\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"
}