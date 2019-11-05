package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.generated.resources.AssetResource
import org.tokend.sdk.factory.JsonApiToolsProvider
import org.tokend.sdk.utils.extentions.has
import org.tokend.wallet.xdr.AssetPolicy

class AssetsModelTest {
    @Test
    fun singleAsset() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                assetResponse.toByteArray(),
                AssetResource::class.java
        )

        val asset = document.get()

        JsonApiUtil.checkResourceNullability(asset)

        Assert.assertTrue(asset.isFilled())
        Assert.assertNotNull(asset.details.get("name").asText())
        Assert.assertTrue(asset.policies.has(AssetPolicy.BASE_ASSET.value))
        Assert.assertNotNull(asset.owner)
    }

    private val assetResponse = "{\n" +
            "\"data\": {\n" +
            "\"id\": \"BTC\",\n" +
            "\"type\": \"assets\",\n" +
            "\"attributes\": {\n" +
            "\"available_for_issuance\": \"21000000.000000\",\n" +
            "\"details\": {\n" +
            "\"name\": \"Bitcoin\"\n" +
            "},\n" +
            "\"issued\": \"0.000000\",\n" +
            "\"max_issuance_amount\": \"21000000.000000\",\n" +
            "\"pending_issuance\": \"0.000000\",\n" +
            "\"policies\": {\n" +
            "\"value\": 3,\n" +
            "\"flags\": [\n" +
            "{\n" +
            "\"name\": \"transferable\",\n" +
            "\"value\": 1\n" +
            "},\n" +
            "{\n" +
            "\"name\": \"base_asset\",\n" +
            "\"value\": 2\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "\"pre_issuance_asset_signer\": \"GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB\",\n" +
            "\"state\": {\n" +
            "\"name\": \"active\",\n" +
            "\"value\": 0\n" +
            "},\n" +
            "\"trailing_digits\": 6,\n" +
            "\"type\": 0\n" +
            "},\n" +
            "\"relationships\": {\n" +
            "\"owner\": {\n" +
            "\"data\": {\n" +
            "\"id\": \"GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB\",\n" +
            "\"type\": \"accounts\"\n" +
            "}\n" +
            "}\n" +
            "}\n" +
            "},\n" +
            "\"included\": []\n" +
            "}"
}