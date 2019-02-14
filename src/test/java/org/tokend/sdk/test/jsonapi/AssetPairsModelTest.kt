package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.generated.resources.AssetPairResource
import org.tokend.sdk.factory.JsonApiToolsProvider
import org.tokend.sdk.utils.extentions.has
import org.tokend.wallet.xdr.AssetPairPolicy

class AssetPairsModelTest {
    @Test
    fun singlePair() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                assetPairResponse.toByteArray(),
                AssetPairResource::class.java
        )

        val pair = document.get()

        JsonApiUtil.checkResourceNullability(pair)

        Assert.assertTrue(pair.hasAttributes())
        Assert.assertNotNull(pair.baseAsset)
        Assert.assertNotNull(pair.quoteAsset)
        Assert.assertNotEquals(pair.baseAsset.id, pair.quoteAsset.id)
        Assert.assertTrue(pair.policies.has(AssetPairPolicy.TRADEABLE_SECONDARY_MARKET.value))
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
}