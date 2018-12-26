package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.*
import org.junit.Test
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.balances.model.BalanceResource

class BalanceModelTest {

    @Test
    fun singleBalance() {

        val converter = ResourceConverter(
                BalanceResource::class.java,
                AssetResource::class.java
        )

        val document = converter.readDocument(
                Data.balanceResponse.toByteArray(),
                BalanceResource::class.java
        )

        val balance = document.get()

        JsonApiUtil.checkResourceNullability(balance)

        assertTrue(balance.hasAttributes())
        assertFalse(balance.asset.hasAttributes())
        assertFalse(balance.account.hasAttributes())
    }

    @Test
    fun balanceList() {

        val converter = ResourceConverter(
                BalanceResource::class.java,
                AssetResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.balanceListResponseUnincluded.toByteArray(),
                BalanceResource::class.java
        )

        val balances = document.get()

        JsonApiUtil.checkResourceNullability(balances)

        assertTrue(balances.isNotEmpty())

        val balance = balances.first()

        assertTrue(balance.hasAttributes())
        assertFalse(balance.asset.hasAttributes())
    }
}