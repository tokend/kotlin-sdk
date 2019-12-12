package org.tokend.sdk.api.integrations.fiat

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.integrations.fiat.model.FiatInvoiceAttributes
import org.tokend.sdk.api.ingester.transactions.model.SubmitTransactionRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FiatService {
    @POST("integrations/fiat/pay")
    @JvmSuppressWildcards
    fun submitBidTransaction(@Body body: SubmitTransactionRequestBody):
            Call<DataEntity<AttributesEntity<FiatInvoiceAttributes>>>
}