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

    private val assetPairResponse = "{\n" +
            "\"data\": {\n" +
            "\"id\": \"0CC7D7:D648B1\",\n" +
            "\"type\": \"asset-pairs\",\n" +
            "\"attributes\": {\n" +
            "\"price\": \"5.500000\",\n" +
            "\"policies\": {\n" +
            "\"value\": 1,\n" +
            "\"flags\": [\n" +
            "{\n" +
            "\"name\": \"tradeable_secondary_market\",\n" +
            "\"value\": 1\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "},\n" +
            "\"relationships\": {\n" +
            "\"base_asset\": {\n" +
            "\"data\": {\n" +
            "\"id\": \"0CC7D7\",\n" +
            "\"type\": \"assets\"\n" +
            "}\n" +
            "},\n" +
            "\"quote_asset\": {\n" +
            "\"data\": {\n" +
            "\"id\": \"D648B1\",\n" +
            "\"type\": \"assets\"\n" +
            "}\n" +
            "}\n" +
            "}\n" +
            "},\n" +
            "\"included\": []\n" +
            "}"
}