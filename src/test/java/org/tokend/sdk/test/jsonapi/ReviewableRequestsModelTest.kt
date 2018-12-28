package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.base.UnknownResource
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource
import org.tokend.sdk.api.v2.requests.model.details.AssetCreateRequestDetailsResource

class ReviewableRequestsModelTest {
    @Test
    fun singleRequest() {
        val converter = ResourceConverter(
                ReviewableRequestResource::class.java,
                AssetCreateRequestDetailsResource::class.java,
                AccountResource::class.java,
                UnknownResource::class.java
        )

        val document = converter.readDocument(
                requestAssetCreateResponse.toByteArray(),
                ReviewableRequestResource::class.java
        )

        val request = document.get()

        JsonApiUtil.checkResourceNullability(request)

        Assert.assertTrue(request.hasAttributes())
        Assert.assertTrue(request.getDetails<AssetCreateRequestDetailsResource>().hasAttributes())
    }

    private val requestAssetCreateResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"id\":\"212\",\n" +
            "      \"type\":\"requests\",\n" +
            "      \"attributes\":{  \n" +
            "         \"created_at\":\"2018-04-07T17:40:11Z\",\n" +
            "         \"state\":\"approved\",\n" +
            "         \"state_i\":3,\n" +
            "         \"hash\":\"2b973d51a10646505745aa7f1c860a383ea7361dac1b41779cc079dc385870dc\",\n" +
            "         \"reject_reason\":\"\",\n" +
            "         \"reference\":\"BTC\",\n" +
            "         \"updated_at\":\"2018-04-07T17:40:11Z\"\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"requestor\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"reviewer\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"request_details\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"asset_create_request_details\",\n" +
            "               \"id\":\"212\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"type\":\"asset_create_request_details\",\n" +
            "         \"id\":\"212\",\n" +
            "         \"attributes\":{  \n" +
            "            \"code\":\"BTC\",\n" +
            "            \"policies\":[  \n" +
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
            "            \"pre_issued_asset_signer\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "            \"max_issuance_amount\":\"9223372036854.775807\",\n" +
            "            \"initial_preissued_amount\":\"0.000000\",\n" +
            "            \"details\":{  \n" +
            "               \"logo\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"type\":\"logo_type\",\n" +
            "                  \"url\":\"logo_url\"\n" +
            "               },\n" +
            "               \"name\":\"BTC name\",\n" +
            "               \"terms\":{  \n" +
            "                  \"key\":\"\",\n" +
            "                  \"name\":\"\",\n" +
            "                  \"type\":\"\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}