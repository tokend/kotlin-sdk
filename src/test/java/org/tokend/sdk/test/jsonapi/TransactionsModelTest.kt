package org.tokend.sdk.test.jsonapi

import org.junit.Test
import org.tokend.sdk.api.generated.resources.TransactionResource
import org.tokend.sdk.factory.JsonApiToolsProvider

class TransactionsModelTest {

    @Test
    fun singleTransaction() {
        val document = JsonApiToolsProvider.getResourceConverter().readDocument(
                transactionResponse.toByteArray(),
                TransactionResource::class.java
        )

        val transaction = document.get()

        JsonApiUtil.checkResourceNullability(transaction)
    }

    private val transactionResponse = "{\n" +
            "  \"data\": {\n" +
            "    \"type\": \"transactions\",\n" +
            "    \"id\": \"b21064dfcc521fe7f7e56d00630aa2675c9b3f5b1a8dd92f74b3c52062d5adb4\",\n" +
            "    \"attributes\": {\n" +
            "      \"paging_token\": \"73014452224\",\n" +
            "      \"hash\": \"b21064dfcc521fe7f7e56d00630aa2675c9b3f5b1a8dd92f74b3c52062d5adb4\",\n" +
            "      \"ledger\": 17,\n" +
            "      \"created_at\": \"2018-04-07T17:40:16Z\",\n" +
            "      \"source_account\": \"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "      \"fee_paid\": 0,\n" +
            "      \"operation_count\": 1,\n" +
            "      \"envelope_xdr\": \"AAAAAP4DpOIcoI8urCJITRZtEDS0wzyPuGojb7AbKpHcMR1gAAAAAAAAAAAAAAAAAAAAAAAAAABa0i7rAAAAAAAAAAEAAAAAAAAADwAAAAAAAAADRVRIAAAAAANTVU4AAAAAAEk9itAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAdwxHWAAAABAM8AA5OEsbzgc7MVXxI4eaGw6oL275/R2ZSvJQkxQbbxrcbB2TeXPaUDRZmaCK9QcAUVAIaHRajY+9htJwPM3Bg==\",\n" +
            "      \"result_xdr\": \"AAAAAAAAAAAAAAAAAAAAAQAAAAAAAAAPAAAAAAAAAABJPYrQAAAAAAAAAAA=\",\n" +
            "      \"result_meta_xdr\": \"AAAAAAAAAAEAAAABAAAAAAAAABEAAAAMAAAAA0VUSAAAAAADU1VOAAAAAABJPYrQAAAAAEk9itAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\n" +
            "      \"fee_meta_xdr\": \"AAAAAA==\",\n" +
            "      \"memo_type\": \"none\",\n" +
            "      \"signatures\": [\n" +
            "        \"M8AA5OEsbzgc7MVXxI4eaGw6oL275/R2ZSvJQkxQbbxrcbB2TeXPaUDRZmaCK9QcAUVAIaHRajY+9htJwPM3Bg==\"\n" +
            "      ],\n" +
            "      \"valid_after\": \"1970-01-01T00:00:00Z\",\n" +
            "      \"valid_before\": \"2018-04-14T16:40:12Z\"\n" +
            "    }\n" +
            "  }\n" +
            "}"
}