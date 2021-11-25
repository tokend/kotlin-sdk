package org.tokend.sdk.api.v3.balances

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.BalanceResource
import org.tokend.sdk.api.v3.model.generated.resources.ConvertedBalancesCollectionResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BalancesService {
    @GET("v3/balances")
    @JvmSuppressWildcards
    fun getBalances(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<BalanceResource>>>

    @GET("v3/balances/{id}")
    @JvmSuppressWildcards
    fun getBalanceById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<BalanceResource>>

    @GET("v3/accounts/{accountId}/converted_balances/{assetCode}")
    @JvmSuppressWildcards
    fun getConvertedBalances(@Path("accountId") id: String,
                             @Path("assetCode") assetCode: String,
                             @QueryMap query: Map<String, Any>
    ): Call<JSONAPIDocument<ConvertedBalancesCollectionResource>>
}