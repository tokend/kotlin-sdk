package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.*
import org.tokend.sdk.api.accounts.model.limits.LimitsResponse
import org.tokend.sdk.api.base.model.Page
import org.tokend.sdk.api.trades.model.Offer
import retrofit2.Call
import retrofit2.http.*

interface AccountsService {
    @GET("accounts/{accountId}")
    fun getAccount(@Path("accountId") accountId: String?):
            Call<Account>

    @GET("accounts/{accountId}/balances")
    fun getBalances(@Path("accountId") accountId: String):
            Call<List<Account.Balance>>

    @GET("accounts/{accountId}/balances/details")
    fun getBalancesDetails(@Path("accountId") accountId: String?):
            Call<List<SimpleBalanceDetails>>

    @JvmSuppressWildcards
    @GET("accounts/{accountId}/payments")
    fun getPayments(@Path("accountId") accountId: String,
                    @QueryMap query: Map<String, Any>?): Call<Page<UnifiedOperationRecord>>

    @JvmSuppressWildcards
    @GET("accounts/{accountId}/offers")
    fun getPendingOffers(@Path("accountId") accountId: String?,
                         @QueryMap query: Map<String, Any>?): Call<Page<Offer>>

    @POST("details")
    fun getAccountsDetails(@Body body: AccountsDetailsRequestBody)
            : Call<AccountsDetailsResponse>

    @GET("user_id")
    fun getAccountIdByEmail(@Query("email") email: String): Call<AccountIdResponse>

    @GET("accounts/{accountId}/limits")
    fun getLimits(@Path("accountId") accountId: String): Call<LimitsResponse>

    @POST("accounts")
    fun createAccount(@Body body: CreateAccountRequestBody): Call<Void>
}