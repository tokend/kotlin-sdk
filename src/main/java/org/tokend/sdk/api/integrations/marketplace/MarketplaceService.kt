package org.tokend.sdk.api.integrations.marketplace

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.marketplace.model.MarketplaceInvoiceAttributes
import org.tokend.sdk.api.v3.transactions.model.SubmitTransactionRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface MarketplaceService {
    @POST("integrations/marketplace/buy")
    @JvmSuppressWildcards
    fun submitBidTransaction(@Body body: SubmitTransactionRequestBody):
            Call<DataEntity<AttributesEntity<MarketplaceInvoiceAttributes>>>
}