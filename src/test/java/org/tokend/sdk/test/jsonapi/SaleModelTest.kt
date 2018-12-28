package org.tokend.sdk.test.jsonapi

import com.github.jasminb.jsonapi.ResourceConverter
import org.junit.Assert.*
import org.junit.Test
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import org.tokend.sdk.api.v2.assets.model.AssetResource
import org.tokend.sdk.api.v2.sales.model.SaleResource

class SaleModelTest {

    @Test
    fun singleSale() {

        val converter = ResourceConverter(
                SaleResource::class.java,
                AccountResource::class.java,
                AssetResource::class.java
        )

        val document = converter.readDocument(
                Data.saleResponseUnincluded.toByteArray(),
                SaleResource::class.java
        )

        val sale = document.get()

        JsonApiUtil.checkResourceNullability(sale)
        assertTrue(sale.hasAttributes())
        assertFalse(sale.baseAsset.hasAttributes())
        assertFalse(sale.owner.hasAttributes())
        assertFalse(sale.quoteAssets.first().hasAttributes())
        assertTrue(sale.balances.isNotEmpty())
    }

    @Test
    fun salesList() {

        val converter = ResourceConverter(
                SaleResource::class.java,
                AccountResource::class.java,
                AssetResource::class.java
        )

        val document = converter.readDocumentCollection(
                Data.salesListResponseUnincluded.toByteArray(),
                SaleResource::class.java
        )

        val sales = document.get()

        JsonApiUtil.checkResourceNullability(sales)

        assertTrue(sales.isNotEmpty())

        val sale = sales.first()

        assertTrue(sale.hasAttributes())
        assertFalse(sale.baseAsset.hasAttributes())
        assertFalse(sale.owner.hasAttributes())
        assertFalse(sale.quoteAssets.first().hasAttributes())
        assertTrue(sale.balances.isNotEmpty())
    }
}