package org.tokend.sdk.api.v3.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.AccountResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface AccountsServiceV3 {
    @GET("v3/accounts")
    @JvmSuppressWildcards
    fun getAccounts(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<AccountResource>>>

    @GET("v3/accounts/{id}")
    @JvmSuppressWildcards
    fun getAccountById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<AccountResource>>
}