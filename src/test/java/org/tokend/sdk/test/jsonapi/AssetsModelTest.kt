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
        Assert.assertTrue(asset.policies.has(AssetPolicy.STATS_QUOTE_ASSET.value))
        Assert.assertNotNull(asset.owner)
    }

    private val assetResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"USD\",\n" +
            "      \"type\":\"assets\",\n" +
            "      \"relationships\":{  \n" +
            "         \"owner\":{  \n" +
            "            \"data\":{  \n" +
            "               \"id\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\",\n" +
            "               \"type\":\"accounts\"\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"attributes\":{  \n" +
            "         \"pre_issuance_asset_signer\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\",\n" +
            "         \"max_issuance_amount\":\"1000.000001\",\n" +
            "         \"available_for_issuance\":\"20.000000\",\n" +
            "         \"issued\":\"10.000000\",\n" +
            "         \"type\":\"1\",\n" +
            "         \"pending_issuance\":\"123.000123\",\n" +
            "         \"policies\":{  \n" +
            "            \"value\":4,\n" +
            "            \"flags\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"transfarable\",\n" +
            "                  \"value\":2\n" +
            "               }\n" +
            "            ]\n" +
            "         },\n" +
            "         \"trailing_digits\":2,\n" +
            "           \"details\":{  \n" +
            "            \"logo\":{  \n" +
            "               \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "               \"name\":\"bitcoin_logo.png\",\n" +
            "               \"type\":\"image/png\"\n" +
            "            },\n" +
            "            \"name\":\"Bitcoin\",\n" +
            "            \"terms\":{  \n" +
            "               \"key\":\"\",\n" +
            "               \"name\":\"\",\n" +
            "               \"type\":\"\"\n" +
            "            }}}\n" +
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