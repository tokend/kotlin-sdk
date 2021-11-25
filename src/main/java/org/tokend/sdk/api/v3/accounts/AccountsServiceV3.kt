package org.tokend.sdk.api.v3.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.AccountResource
import org.tokend.sdk.api.v3.model.generated.resources.AccountRoleResource
import org.tokend.sdk.api.v3.model.generated.resources.AccountRuleResource
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

    @GET("v3/account_roles")
    @JvmSuppressWildcards
    fun getAccountRoles(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<AccountRoleResource>>>

    @GET("v3/account_roles/{id}")
    @JvmSuppressWildcards
    fun getAccountRoleById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<AccountRoleResource>>

    @GET("v3/account_rules")
    @JvmSuppressWildcards
    fun getAccountRules(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<AccountRuleResource>>>

    @GET("v3/account_rules/{id}")
    fun getAccountRuleById(@Path("id") id: String): Call<JSONAPIDocument<AccountRuleResource>>
}