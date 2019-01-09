package org.tokend.sdk.api.v2.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.accounts.model.AccountResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AccountsServiceV2 {
    @GET("accounts")
    @JvmSuppressWildcards
    fun getAccounts(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<AccountResource>>>

    @GET("accounts/{id}")
    @JvmSuppressWildcards
    fun getAccountById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<AccountResource>>
}