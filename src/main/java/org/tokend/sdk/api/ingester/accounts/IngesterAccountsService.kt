package org.tokend.sdk.api.ingester.accounts

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.ingester.generated.resources.SignerResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface IngesterAccountsService {
    @GET("horizon/accounts/{id}/signers")
    @JvmSuppressWildcards
    fun getAccountSigners(@Path("id") accountId: String,
                          @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<SignerResource>>>
}