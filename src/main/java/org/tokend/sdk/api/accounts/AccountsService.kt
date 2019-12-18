package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.CreateAccountRequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AccountsService {
    @POST("api/accounts")
    fun createAccount(@Body body: CreateAccountRequestBody): Call<Void>
}