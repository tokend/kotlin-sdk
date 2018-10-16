package org.tokend.sdk.api.transactions

import org.tokend.sdk.api.transactions.model.SubmitTransactionResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TransactionsService {
    @FormUrlEncoded
    @POST("transactions")
    fun pushTransaction(@Field("tx") envelopeXdrBase64: String):
            Call<SubmitTransactionResponse>
}