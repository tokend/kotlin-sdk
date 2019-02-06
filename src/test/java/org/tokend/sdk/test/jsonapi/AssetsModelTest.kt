package org.tokend.sdk.test.jsonapi

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.factory.JsonApiToolsProvider
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

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.details.name)
        assertTrue(asset.hasPolicy(AssetPolicy.STATS_QUOTE_ASSET))
    }

    @Test
    fun assetsList() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocumentCollection(
                assetListResponse.toByteArray(),
                AssetResource::class.java
        )

        assertNotNull(document.links)

        val assets = document.get()

        JsonApiUtil.checkResourceNullability(assets)

        assertTrue(assets.isNotEmpty())

        val asset = assets.first()

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.details.name)
        assertTrue(assets.all { it.hasPolicy(AssetPolicy.STATS_QUOTE_ASSET) })
    }

    private val assetResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"assets\",\n" +
            "      \"id\":\"BTC\",\n" +
            "      \"attributes\":{  \n" +
            "         \"available_for_issuance\":\"1000000000.000000\",\n" +
            "         \"details\":{  \n" +
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
            "            }\n" +
            "         },\n" +
            "         \"issued\":\"0.000000\",\n" +
            "         \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "         \"pending_issuance\":\"0.000000\",\n" +
            "         \"policies\":{  \n" +
            "            \"flags\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"stats_quote_asset\",\n" +
            "                  \"value\":4\n" +
            "               }\n" +
            "            ],\n" +
            "            \"mask\":4\n" +
            "         },\n" +
            "         \"pre_issuance_asset_signer\":\"GBGC5DS6FGZOCDUKV43PTMTNG4H6LMLBTW3XWLXOUVSP7VWI4QO3E6C3\",\n" +
            "         \"trailing_digits\":6\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"owner\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    private val assetListResponse = "{  \n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"assets\",\n" +
            "         \"id\":\"BTC\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available_for_issuance\":\"1000000000.000000\",\n" +
            "            \"details\":{  \n" +
            "               \"logo\":{  \n" +
            "                  \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "                  \"name\":\"bitcoin_logo.png\",\n" +
            "                  \"type\":\"image/png\"\n" +
            "               },\n" +
            "               \"name\":\"Bitcoin\",\n" +
            "               \"terms\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"name\":\"\",\n" +
            "                  \"type\":\"\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"issued\":\"0.000000\",\n" +
            "            \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "            \"pending_issuance\":\"0.000000\",\n" +
            "            \"policies\":{  \n" +
            "               \"flags\":[  \n" +
            "                  {  \n" +
            "                     \"name\":\"stats_quote_asset\",\n" +
            "                     \"value\":4\n" +
            "                  }\n" +
            "               ],\n" +
            "               \"mask\":4\n" +
            "            },\n" +
            "            \"pre_issuance_asset_signer\":\"GBGC5DS6FGZOCDUKV43PTMTNG4H6LMLBTW3XWLXOUVSP7VWI4QO3E6C3\",\n" +
            "            \"trailing_digits\":6\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"owner\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"assets\",\n" +
            "         \"id\":\"BTC20317\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available_for_issuance\":\"1000000000.000000\",\n" +
            "            \"details\":{  \n" +
            "               \"logo\":{  \n" +
            "                  \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "                  \"name\":\"ethereum_logo.png\",\n" +
            "                  \"type\":\"image/png\"\n" +
            "               },\n" +
            "               \"name\":\"\",\n" +
            "               \"terms\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"name\":\"\",\n" +
            "                  \"type\":\"\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"issued\":\"0.000000\",\n" +
            "            \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "            \"pending_issuance\":\"0.000000\",\n" +
            "            \"policies\":{  \n" +
            "               \"flags\":[  \n" +
            "                  {  \n" +
            "                     \"name\":\"stats_quote_asset\",\n" +
            "                     \"value\":4\n" +
            "                  }\n" +
            "               ],\n" +
            "               \"mask\":4\n" +
            "            },\n" +
            "            \"pre_issuance_asset_signer\":\"GBBTS5IVCOBNZ7D6LSTIB7BBWOAYGZFWHGLAP3WBU3N4A4ALHCGFNRTM\",\n" +
            "            \"trailing_digits\":6\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"owner\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GBFSW4ZSDLNXUKS77LGMQVDNJUCU77ZUZX7AOIWLDF7OPX5F7OPFR67O\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ],\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"type\":\"accounts\",\n" +
            "         \"id\":\"GBA4EX43M25UPV4WIE6RRMQOFTWXZZRIPFAI5VPY6Z2ZVVXVWZ6NEOOB\"\n" +
            "      },\n" +
            "      {  \n" +
            "         \"type\":\"accounts\",\n" +
            "         \"id\":\"GBFSW4ZSDLNXUKS77LGMQVDNJUCU77ZUZX7AOIWLDF7OPX5F7OPFR67O\"\n" +
            "      }\n" +
            "   ],\n" +
            "   \"links\":{  \n" +
            "      \"next\":\"/v2/assets?include=owner&page[number]=1&page[limit]=15\",\n" +
            "      \"self\":\"/v2/assets?include=owner&page[number]=0&page[limit]=15\"\n" +
            "   }\n" +
            "}"
}