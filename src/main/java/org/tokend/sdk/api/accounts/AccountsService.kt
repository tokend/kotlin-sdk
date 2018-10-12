package org.tokend.sdk.api.accounts

import org.tokend.sdk.api.accounts.model.AccountsDetailsResponse
import org.tokend.sdk.api.trades.model.Offer
import org.tokend.sdk.api.models.PaymentRecord
import org.tokend.sdk.api.models.SimpleBalanceDetails
import org.tokend.sdk.api.requests.models.AccountsDetailsRequestBody
import org.tokend.sdk.api.accounts.model.AccountResponse
import org.tokend.sdk.api.models.AccountIdResponse
import org.tokend.sdk.api.responses.Page
import retrofit2.Call
import retrofit2.http.*

interface AccountsService {
    @GET("accounts/{accountId}")
    fun getAccount(@Path("accountId") accountId: String?):
            Call<AccountResponse>

    /**
     * Will return empty account with only 'signers' filled.
     */
    @GET("accounts/{accountId}/signers")
    fun getSigners(@Path("accountId") accountId: String?):
            Call<AccountResponse>

    @GET("accounts/{accountId}/balances")
    fun getBalances(@Path("accountId") accountId: String):
            Call<List<AccountResponse.Balance>>

    @GET("accounts/{accountId}/balances/details")
    fun getBalancesDetails(@Path("accountId") accountId: String?):
            Call<List<SimpleBalanceDetails>>

    @JvmSuppressWildcards
    @GET("accounts/{accountId}/payments")
    fun getPayments(@Path("accountId") accountId: String,
                    @QueryMap query: Map<String, Any>?): Call<Page<PaymentRecord>>

    @JvmSuppressWildcards
    @GET("accounts/{accountId}/offers")
    fun getPendingOffers(@Path("accountId") accountId: String?,
                         @QueryMap query: Map<String, Any>?): Call<Page<Offer>>

    @POST("details")
    fun getAccountsDetails(@Body body: AccountsDetailsRequestBody)
            : Call<AccountsDetailsResponse>

    @GET("user_id")
    fun getAccountIdByEmail(@Query("email") email: String): Call<AccountIdResponse>
}