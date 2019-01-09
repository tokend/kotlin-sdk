package org.tokend.sdk.test.jsonapi

import org.junit.Assert
import org.junit.Test
import org.tokend.sdk.api.v2.operations.model.OperationResource
import org.tokend.sdk.api.v2.operations.model.details.CreateAccountOperationDetailsResource
import org.tokend.sdk.factory.JsonApiFactory

class OperationModelTest {
    @Test
    fun singleOperation() {
        val document = JsonApiFactory().getResourceConverter().readDocument(
                operationResponse.toByteArray(),
                OperationResource::class.java
        )

        val operation = document.get()

        JsonApiUtil.checkResourceNullability(operation)

        Assert.assertTrue(operation.hasAttributes())
        Assert.assertTrue(operation.getDetails<CreateAccountOperationDetailsResource>().hasAttributes())
    }

    private val operationResponse = "{  \n" +
            "   \"data\":{  \n" +
            "      \"type\":\"operations\",\n" +
            "      \"id\":\"667072845582337\",\n" +
            "      \"attributes\":{  \n" +
            "         \"transaction_id\":\"667072845582336\",\n" +
            "         \"operation_type\":\"create_account\",\n" +
            "         \"operation_type_i\":0,\n" +
            "         \"state_i\":2,\n" +
            "         \"state\":\"success\",\n" +
            "         \"identifier\":\"0\",\n" +
            "         \"ledger_close_time\":\"2018-05-19T14:19:11Z\"\n" +
            "      },\n" +
            "      \"relationships\":{  \n" +
            "         \"source\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"accounts\",\n" +
            "               \"id\":\"GA4CZMOLWKO6RBKT77V2W4JGKVKYHSUAATVDJYPUYVGTYQ7BLTVH5CBP\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"operation_details\":{  \n" +
            "            \"data\":{  \n" +
            "               \"type\":\"create_account_op_details\",\n" +
            "               \"id\":\"667072845582337\"\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"included\":[  \n" +
            "      {  \n" +
            "         \"type\":\"create_account_op_details\",\n" +
            "         \"id\":\"667072845582337\",\n" +
            "         \"attributes\":{  \n" +
            "            \"funder\":\"GA4CZMOLWKO6RBKT77V2W4JGKVKYHSUAATVDJYPUYVGTYQ7BLTVH5CBP\",\n" +
            "            \"account\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\",\n" +
            "            \"account_type\":5\n" +
            "         },\n" +
            "         \"relationships\":{  \n" +
            "            \"participants\":{  \n" +
            "               \"data\":[  \n" +
            "                  {  \n" +
            "                     \"type\":\"accounts\",\n" +
            "                     \"id\":\"GA4CZMOLWKO6RBKT77V2W4JGKVKYHSUAATVDJYPUYVGTYQ7BLTVH5CBP\"\n" +
            "                  },\n" +
            "                  {  \n" +
            "                     \"type\":\"accounts\",\n" +
            "                     \"id\":\"GAA5WRH3KOAXZPW6PR3BKQSOG3KN3VAW2BO72PC6SHLSUSBW77DQHUB3\"\n" +
            "                  }\n" +
            "               ]\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}