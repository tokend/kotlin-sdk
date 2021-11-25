package org.tokend.sdk.api.v3.signers

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.SignerResource
import org.tokend.sdk.api.v3.model.generated.resources.SignerRoleResource
import org.tokend.sdk.api.v3.model.generated.resources.SignerRuleResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface SignersServiceV3 {
    @GET("v3/accounts/{id}/signers")
    @JvmSuppressWildcards
    fun getAccountSigners(@Path("id") accountId: String,
                          @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SignerResource>>>

    @GET("v3/signer_roles")
    @JvmSuppressWildcards
    fun getSignerRoles(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SignerRoleResource>>>

    @GET("v3/signer_roles/{id}")
    @JvmSuppressWildcards
    fun getSignerRoleById(@Path("id") id: String,
                          @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<SignerRoleResource>>

    @GET("v3/signer_rules")
    @JvmSuppressWildcards
    fun getSignerRules(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SignerRuleResource>>>

    @GET("v3/signer_rules/{id}")
    @JvmSuppressWildcards
    fun getSignerRuleById(@Path("id") id: String,
                          @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<SignerRuleResource>>
}