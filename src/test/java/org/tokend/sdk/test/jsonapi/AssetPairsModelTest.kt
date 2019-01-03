package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.assetpairs.model.AssetPairResource
import org.tokend.sdk.factory.JsonApiFactory
import org.tokend.wallet.xdr.AssetPairPolicy

class AssetPairsModelTest {
    @Test
    fun singlePair() {
        val document = JsonApiFactory().getResourceConverter().readDocument(
                assetPairResponse.toByteArray(),
                AssetPairResource::class.java
        )

        val pair = document.get()

        JsonApiUtil.checkResourceNullability(pair)

        Assert.assertTrue(pair.hasAttributes())
        Assert.assertNotNull(pair.baseAsset)
        Assert.assertNotNull(pair.quoteAsset)
        Assert.assertNotEquals(pair.baseAsset.id, pair.quoteAsset.id)
        Assert.assertTrue(pair.hasPolicy(AssetPairPolicy.TRADEABLE_SECONDARY_MARKET))
    }

    @Test
    fun pairsList() {
        val document = JsonApiFactory().getResourceConverter().readDocumentCollection(
                assetPairsListResponse.toByteArray(),
                AssetPairResource::class.java
        )

        Assert.assertNotNull(document.meta)
        Assert.assertNotNull(document.links)

        val pairs = document.get()

        JsonApiUtil.checkResourceNullability(pairs)

        Assert.assertTrue(pairs.isNotEmpty())

        val pair = pairs.first()

        Assert.assertTrue(pair.hasAttributes())
        Assert.assertNotNull(pair.baseAsset)
        Assert.assertNotNull(pair.quoteAsset)
        Assert.assertNotEquals(pair.baseAsset.id, pair.quoteAsset.id)
    }

    private val assetPairResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"asset_pairs\",\n" +
            "      \"id\":\"BTC-HHCT\",\n" +
            "      \"attributes\":{  \n" +
            "         \"price\":\"1.000000\",\n" +
            "         \"policy_i\":1,\n" +
            "         \"policies\":[  \n" +
            "            {  \n" +
            "               \"name\":\"AssetPairPolicyTradeableSecondaryMarket\",\n" +
            "               \"value\":1\n" +
            "            }\n" +
            "         ]\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"base_asset\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"assets\",\n" +
            "               \"id\":\"HHCT\"\n" +
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

    private val assetPairsListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/asset_pairs?page=3\",\n" +
            "      \"first\":\"https://api.com/asset_pairs?page=1\",\n" +
            "      \"prev\":\"https://api.com/asset_pairs?page=2\",\n" +
            "      \"next\":\"https://api.com/asset_pairs?page=4\",\n" +
            "      \"last\":\"https://api.com/asset_pairs?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"asset_pairs\",\n" +
            "         \"id\":\"BTC-HHCT\",\n" +
            "         \"attributes\":{  \n" +
            "            \"price\":\"1.000000\",\n" +
            "            \"policy_i\":0,\n" +
            "            \"policies\":[  \n" +
            "\n" +
            "            ]\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"base_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"HHCT\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"quote_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"asset_pairs\",\n" +
            "         \"id\":\"ETH-USD\",\n" +
            "         \"attributes\":{  \n" +
            "            \"current_price\":\"294.470000\",\n" +
            "            \"price\":\"294.470000\",\n" +
            "            \"policy_i\":0,\n" +
            "            \"policies\":[  \n" +
            "\n" +
            "            ]\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"base_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"HHCT\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"quote_asset\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"assets\",\n" +
            "                  \"id\":\"BTC\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}