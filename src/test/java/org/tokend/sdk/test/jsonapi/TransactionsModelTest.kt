package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.*
import org.junit.Test
import org.tokend.sdk.api.v2.transactions.model.TransactionResource

class TransactionsModelTest {

    @Test
    fun singleTransaction() {

        val converter = ResourceConverter(
                TransactionResource::class.java
        )

        val document = converter.readDocument(
                transactionResponse.toByteArray(),
                TransactionResource::class.java
        )

        val transaction = document.get()

        JsonApiUtil.checkResourceNullability(transaction)

        assertTrue(transaction.hasAttributes())
        assertTrue(transaction.signatures.isNotEmpty())
    }

    @Test
    fun transactionsList() {

        val converter = ResourceConverter(
                TransactionResource::class.java
        )

        val document = converter.readDocumentCollection(
                transactionsListResponse.toByteArray(),
                TransactionResource::class.java
        )

        val transactions = document.get()

        JsonApiUtil.checkResourceNullability(transactions)

        assertTrue(transactions.isNotEmpty())

        val transaction = transactions.first()

        assertTrue(transaction.hasAttributes())
        assertTrue(transaction.signatures.isNotEmpty())
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

    private val transactionsListResponse = "{\n" +
            "  \"meta\": {\n" +
            "    \"total_pages\": 10,\n" +
            "    \"latest_ledger\": {\n" +
            "      \"sequence\": 19,\n" +
            "      \"closed_at\": \"2018-04-07T17:40:26Z\"\n" +
            "    }\n" +
            "  },\n" +
            "  \"links\": {\n" +
            "    \"self\": \"https://api.com/transactions?page=3\",\n" +
            "    \"first\": \"https://api.com/transactions?page=1\",\n" +
            "    \"prev\": \"https://api.com/transactions?page=2\",\n" +
            "    \"next\": \"https://api.com/transactions?page=4\",\n" +
            "    \"last\": \"https://api.com/transactions?page=13\"\n" +
            "  },\n" +
            "  \"data\": [\n" +
            "    {\n" +
            "      \"type\": \"transactions\",\n" +
            "      \"id\": \"d3275938107d7dd585ef44b717e67597e0c736591b2901708c90162ea94e4e0c\",\n" +
            "      \"attributes\": {\n" +
            "        \"paging_token\": \"73014452224\",\n" +
            "        \"hash\": \"d3275938107d7dd585ef44b717e67597e0c736591b2901708c90162ea94e4e0c\",\n" +
            "        \"ledger\": 16,\n" +
            "        \"created_at\": \"2018-04-07T17:40:11Z\",\n" +
            "        \"source_account\": \"GD7AHJHCDSQI6LVMEJEE2FTNCA2LJQZ4R64GUI3PWANSVEO4GEOWB636\",\n" +
            "        \"fee_paid\": 0,\n" +
            "        \"operation_count\": 1,\n" +
            "        \"envelope_xdr\": \"AAAAAP4DpOIcoI8urCJITRZtEDS0wzyPuGojb7Ab....\",\n" +
            "        \"result_xdr\": \"AAAAAAAAAAAAAAAAAAAAAQAAAAAAAAALAAAAAAAAAAAA...\",\n" +
            "        \"result_meta_xdr\": \"AAAAAAAAAAEAAAAGAAAAAAAAABAAAAAPAAAAAAAA...\",\n" +
            "        \"fee_meta_xdr\": \"AAAAAA==\",\n" +
            "        \"memo_type\": \"none\",\n" +
            "        \"signatures\": [\n" +
            "          \"Pk2+1r5puXM5OVGkRoYke6S+78xTFVT0YWVlknHWFpK1IqpYBvaHzJQWiLftJWgH/J5hLfyHoYFjaNKrl6dyCA==\"\n" +
            "        ],\n" +
            "        \"valid_after\": \"1970-01-01T00:00:00Z\",\n" +
            "        \"valid_before\": \"2018-04-14T16:40:07Z\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]\n" +
            "}"
}