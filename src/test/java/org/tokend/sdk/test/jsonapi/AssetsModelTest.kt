package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.factory.JsonApiFactory
import org.tokend.wallet.xdr.AssetPolicy

class AssetsModelTest {
    @Test
    fun singleAsset() {
        val converter = ResourceConverter(
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                assetResponse.toByteArray(),
                AssetResource::class.java
        )

        val asset = document.get()

        JsonApiUtil.checkResourceNullability(asset)

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.logoLinks.related)
        assertNotNull(asset.termsLinks.related)
        assertNotNull(asset.details.name)
        assertTrue(asset.hasPolicy(AssetPolicy.TRANSFERABLE))
    }

    @Test
    fun assetsList() {
        val converter = ResourceConverter(
                JsonApiFactory().getBaseObjectMapper(),
                AssetResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocumentCollection(
                assetListResponse.toByteArray(),
                AssetResource::class.java
        )

        assertNotNull(document.meta)
        assertNotNull(document.links)

        val assets = document.get()

        JsonApiUtil.checkResourceNullability(assets)

        assertTrue(assets.isNotEmpty())

        val asset = assets.first()

        assertTrue(asset.hasAttributes())
        assertNotNull(asset.logoLinks.related)
        assertNotNull(asset.termsLinks.related)
        assertNotNull(asset.details.name)
        assertTrue(asset.hasPolicy(AssetPolicy.TRANSFERABLE))
    }

    private val assetResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"assets\",\n" +
            "      \"id\":\"ETH\",\n" +
            "      \"attributes\":{  \n" +
            "         \"available_for_issuance\":\"0.000000\",\n" +
            "         \"preissued_asset_signer\":\"GAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHV4\",\n" +
            "         \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "         \"issued\":\"1000000000.000000\",\n" +
            "         \"pending_issuance\":\"0.000000\",\n" +
            "         \"policy_i\":1,\n" +
            "         \"policies\":[  \n" +
            "            {  \n" +
            "               \"name\":\"AssetPolicyTransferable\",\n" +
            "               \"value\":1\n" +
            "            },\n" +
            "            {  \n" +
            "               \"name\":\"AssetPolicyWithdrawable\",\n" +
            "               \"value\":8\n" +
            "            }\n" +
            "         ],\n" +
            "         \"details\":{  \n" +
            "            \"logo\":{  \n" +
            "               \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "               \"name\":\"ethereum_logo.png\",\n" +
            "               \"type\":\"image/png\"\n" +
            "            },\n" +
            "            \"name\":\"Ethereum\",\n" +
            "            \"terms\":{  \n" +
            "               \"key\":\"\",\n" +
            "               \"name\":\"\",\n" +
            "               \"type\":\"\"\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"owner\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GDGQI3SSB7N7YDBGWCZB3DT7SA23KJWDTYQB5HCYR5VP3EBD6CXQXXG4\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"logo\":{  \n" +
            "            \"links\":{  \n" +
            "               \"related\":\"https://storage.com/key\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"terms\":{  \n" +
            "            \"links\":{  \n" +
            "               \"related\":\"https://storage.com/key\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   }\n" +
            "}"

    private val assetListResponse = "{  \n" +
            "   \"meta\":{  \n" +
            "      \"current_page\":3,\n" +
            "      \"total_pages\":10\n" +
            "   },\n" +
            "   \"links\":{  \n" +
            "      \"self\":\"https://api.com/assets?page=3\",\n" +
            "      \"first\":\"https://api.com/assets?page=1\",\n" +
            "      \"prev\":\"https://api.com/assets?page=2\",\n" +
            "      \"next\":\"https://api.com/assets?page=4\",\n" +
            "      \"last\":\"https://api.com/assets?page=13\"\n" +
            "   },\n" +
            "   \"data\":[  \n" +
            "      {  \n" +
            "         \"type\":\"assets\",\n" +
            "         \"id\":\"ETH\",\n" +
            "         \"attributes\":{  \n" +
            "            \"available_for_issuance\":\"0.000000\",\n" +
            "            \"preissued_asset_signer\":\"GAAQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAHV4\",\n" +
            "            \"max_issuance_amount\":\"1000000000.000000\",\n" +
            "            \"issued\":\"1000000000.000000\",\n" +
            "            \"pending_issuance\":\"0.000000\",\n" +
            "            \"policy_i\":1,\n" +
            "            \"policies\":[  \n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyTransferable\",\n" +
            "                  \"value\":1\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyBaseAsset\",\n" +
            "                  \"value\":2\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyWithdrawable\",\n" +
            "                  \"value\":8\n" +
            "               },\n" +
            "               {  \n" +
            "                  \"name\":\"AssetPolicyTwoStepWithdrawal\",\n" +
            "                  \"value\":16\n" +
            "               }\n" +
            "            ],\n" +
            "            \"details\":{  \n" +
            "               \"external_system_type\":5,\n" +
            "               \"logo\":{  \n" +
            "                  \"key\":\"dpurgh4infnubjhcost7fvjkdwnvkcedflpqdxlxsc5nlsib4diraweq\",\n" +
            "                  \"name\":\"ethereum_logo.png\",\n" +
            "                  \"type\":\"image/png\"\n" +
            "               },\n" +
            "               \"name\":\"Ethereum\",\n" +
            "               \"terms\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"name\":\"\",\n" +
            "                  \"type\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"owner\":{  \n" +
            "               \"data\":{  \n" +
            "                  \"type\":\"accounts\",\n" +
            "                  \"id\":\"GDGQI3SSB7N7YDBGWCZB3DT7SA23KJWDTYQB5HCYR5VP3EBD6CXQXXG4\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"logo\":{  \n" +
            "               \"links\":{  \n" +
            "                  \"related\":\"https://storage.com/key\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"terms\":{  \n" +
            "               \"links\":{  \n" +
            "                  \"related\":\"https://storage.com/key\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}