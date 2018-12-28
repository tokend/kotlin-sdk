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
                Data.transactionResponse.toByteArray(),
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
                Data.transactionsListResponse.toByteArray(),
                TransactionResource::class.java
        )

        val transactions = document.get()

        JsonApiUtil.checkResourceNullability(transactions)

        assertTrue(transactions.isNotEmpty())

        val transaction = transactions.first()

        assertTrue(transaction.hasAttributes())
        assertTrue(transaction.signatures.isNotEmpty())
    }
}