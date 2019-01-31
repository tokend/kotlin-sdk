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
        Assert.assertTrue(operation.transaction.hasAttributes())
        Assert.assertTrue(operation.getDetails<CreateAccountOperationDetailsResource>().hasAttributes())
        Assert.assertFalse(operation.sourceAccount.hasAttributes())
    }

    private val operationResponse = "{\n" +
            "   \"data\":{\n" +
            "      \"id\":\"251929896681473\",\n" +
            "      \"type\":\"operations\",\n" +
            "      \"relationships\":{\n" +
            "         \"tx\":{\n" +
            "            \"data\":{\n" +
            "               \"id\":\"251929896681473\",\n" +
            "               \"type\":\"transactions\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"source\":{\n" +
            "            \"data\":{\n" +
            "               \"id\":\"GAWA6M2YVCKNK76ONCXUDMDCOUH65TPJYLWK5X3ELZ7WU22ZOBUTCGT3\",\n" +
            "               \"type\":\"accounts\"\n" +
            "            }\n" +
            "         },\n" +
            "         \"details\":{\n" +
            "            \"data\":{\n" +
            "               \"id\":\"251929896681473\",\n" +
            "               \"type\":\"operations-create-account\"\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      \"attributes\":{\n" +
            "         \"applied_at\":\"2019-01-23T19:03:08Z\"\n" +
            "      }\n" +
            "   },\n" +
            "   \"included\":[\n" +
            "      {\n" +
            "         \"id\":\"251929896681473\",\n" +
            "         \"type\":\"operations-create-account\",\n" +
            "         \"attributes\":{\n" +
            "            \"account_address\":\"GBQCUGJLGSRQE7EPHG5OB5EVCGCNIKVT56J7ONSDJ6ZSSU625OSIL62J\",\n" +
            "            \"account_type\":{\n" +
            "               \"int\":0,\n" +
            "               \"string\":\"\"\n" +
            "            }\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"type\":\"transactions\",\n" +
            "         \"id\":\"251929896681473\",\n" +
            "         \"attributes\":{\n" +
            "            \"paging_token\":\"73014452224\",\n" +
            "            \"hash\":\"b21064dfcc521fe7f7e56d00630aa2675c9b3f5b1a8dd92f74b3c52062d5adb4\",\n" +
            "            \"ledger\":17,\n" +
            "            \"created_at\":\"2018-04-07T17:40:16Z\",\n" +
            "            \"source_account\":\"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "            \"fee_paid\":0,\n" +
            "            \"operation_count\":1,\n" +
            "            \"envelope_xdr\":\"AAAAAP4DpOIcoI8urCJITRZtEDS0wzyPuGojb7AbKpHcMR1gAAAAAAAAAAAAAAAAAAAAAAAAAABa0i7rAAAAAAAAAAEAAAAAAAAADwAAAAAAAAADRVRIAAAAAANTVU4AAAAAAEk9itAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdwxHWAAAABAM8AA5OEsbzgc7MVXxI4eaGw6oL275/R2ZSvJQkxQbbxrcbB2TeXPaUDRZmaCK9QcAUVAIaHRajY+9htJwPM3Bg==\",\n" +
            "            \"result_xdr\":\"AAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAPAAAAAAAAAABJPYrQAAAAAAAAAAA=\",\n" +
            "            \"result_meta_xdr\":\"AAAAAAAAAAEAAAABAAAAAAAAABEAAAAMAAAAA0VUSAAAAAADU1VOAAAAAABJPYrQAAAAAEk9itAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\n" +
            "            \"fee_meta_xdr\":\"AAAAAA==\",\n" +
            "            \"memo_type\":\"none\",\n" +
            "            \"signatures\":[\n" +
            "               \"M8AA5OEsbzgc7MVXxI4eaGw6oL275/R2ZSvJQkxQbbxrcbB2TeXPaUDRZmaCK9QcAUVAIaHRajY+9htJwPM3Bg==\"\n" +
            "            ],\n" +
            "            \"valid_after\":\"1970-01-01T00:00:00Z\",\n" +
            "            \"valid_before\":\"2018-04-14T16:40:12Z\"\n" +
            "         }\n" +
            "      },\n" +
            "      {\n" +
            "         \"type\":\"accounts\",\n" +
            "         \"id\":\"GAWA6M2YVCKNK76ONCXUDMDCOUH65TPJYLWK5X3ELZ7WU22ZOBUTCGT3\",\n" +
            "         \"relationships\":{\n" +
            "            \"balances\":{\n" +
            "               \"data\":[\n" +
            "                  {\n" +
            "                     \"type\":\"balances\",\n" +
            "                     \"id\":\"BDLXKLLVS6UIQEDYCZKIC5222GGQLCOTHM4BWXVCBDQMJTUWUFUATV2Q\"\n" +
            "                  }\n" +
            "               ]\n" +
            "            },\n" +
            "            \"role\":{\n" +
            "               \"data\":{\n" +
            "                  \"type\":\"roles\",\n" +
            "                  \"id\":\"mocked_role\"\n" +
            "               }\n" +
            "            },\n" +
            "            \"state\":{\n" +
            "               \"data\":{\n" +
            "                  \"type\":\"account_states\",\n" +
            "                  \"id\":\"GBSR6JG5AYSAW7HK6EGJFYVIVN54LVGSY3ZLJ6X3IBQZ766EJABCZQTH\"\n" +
            "               }\n" +
            "            }\n" +
            "         }\n" +
            "      }\n" +
            "   ]\n" +
            "}"
}