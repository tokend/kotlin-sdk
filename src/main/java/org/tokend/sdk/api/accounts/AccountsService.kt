package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.CreateAccountRequestBody
import org.tokend.sdk.api.accounts.model.limits.LimitsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AccountsService {
    @GET("accounts/{accountId}/limits")
    fun getLimits(@Path("accountId") accountId: String): Call<LimitsResponse>

    @POST("accounts")
    fun createAccount(@Body body: CreateAccountRequestBody): Call<Void>
}