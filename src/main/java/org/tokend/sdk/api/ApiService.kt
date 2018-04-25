package org.tokend.sdk.api

import com.google.gson.JsonObject
import org.tokend.sdk.api.models.*
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.api.responses.*
import org.tokend.sdk.api.tfa.TfaBackend
import retrofit2.Call
import retrofit2.http.*
import java.io.Serializable
import java.util.*


interface ApiService {
    @GET("/")
    fun getSystemInfo(): Call<SystemInfo>

    @GET("/accounts/{accountId}")
    fun getAccount(@Path("accountId") accountId: String?):
            Call<AccountResponse>

    /**
     * Will return empty account with only 'signers' filled.
     */
    @GET("/accounts/{accountId}/signers")
    fun getAccountSigners(@Path("accountId") accountId: String?):
            Call<AccountResponse>

    @POST("/wallets/{walletId}/verification")
    fun requestVerificationLink(@Path("walletId") walletId: String?):
            Call<Void>

    @PUT("/wallets/{walletId}/verification")
    fun verifyWallet(@Path("walletId") walletId: String?,
                     @Body data: DataEntity<HashMap<String, Serializable>>):
            Call<ServerResponse<JsonObject, ServerError>>

    @PUT("/wallets/{walletId}")
    fun updateWallet(@Path("walletId") walletId: String?,
                     @Body data: DataEntity<WalletData>):
            Call<ServerResponse<JsonObject, ServerError>>

    @PUT("/users/{accountId}")
    fun createUser(@Path("accountId") accountId: String?,
                   @Body data: DataEntity<HashMap<String, Serializable>>):
            Call<ServerResponse<JsonObject, ServerError>>

    @GET("/users/{accountId}")
    fun getUserInfo(@Path("accountId") accountId: String?):
            Call<ServerResponse<UserInfo, ServerError>>

    @PATCH("/users/{accountId}")
    fun patchUser(@Path("accountId") accountId: String?,
                  @Body data: DataEntity<Map<String, Any>>):
            Call<Void>

    //region TFA
    @GET("/wallets/{walletId}/factors")
    fun getTfaBackends(@Path("walletId") walletId: String?):
            Call<ServerResponse<MutableList<TfaBackend>, ServerError>>

    @POST("/wallets/{walletId}/factors")
    fun createTfaBackend(@Path("walletId") walletId: String?,
                         @Body data: DataEntity<Any>):
            Call<ServerResponse<TfaBackend, ServerError>>

    @FormUrlEncoded
    @POST("/wallets/{walletId}/factors")
    fun createTfaBackend(@Path("walletId") walletId: String?,
                         @Field("type") tfaType: String):
            Call<ServerResponse<TfaBackend, ServerError>>

    @DELETE("/wallets/{walletId}/factors/{id}")
    fun deleteTfaBackend(@Path("walletId") walletId: String?,
                         @Path("id") backendId: Int?):
            Call<ServerResponse<JsonObject, ServerError>>

    @PATCH("/wallets/{walletId}/factors/{id}")
    fun updateTfaBackend(@Path("walletId") walletId: String?,
                         @Path("id") backendId: Int?,
                         @Body data: DataEntity<Any>):
            Call<ServerResponse<JsonObject, ServerError>>

    @PUT("/wallets/{walletId}/factors/{id}/verification")
    fun verifyTfaBackend(@Path("walletId") walletId: String?,
                         @Path("id") backendId: Int?,
                         @Body data: DataEntity<@JvmSuppressWildcards Any>):
            Call<Void>
    //endregion TFA

    @GET("/fees/{feeType}")
    fun getFee(@Path("feeType") feeType: Int,
               @Query("account") accountId: String,
               @Query("asset") asset: String,
               @Query("amount") amount: String?): Call<Fee>

    @GET("/user_id")
    fun getAccountIdByEmail(@Query("email") email: String): Call<JsonObject>

    @GET("/accounts/{accountId}/balances")
    fun getAccountBalances(@Path("accountId") accountId: String):
            Call<List<AccountResponse.Balance>>

    @GET("asset_pairs")
    fun getAssetPairs(): Call<List<AssetPair>>

    @GET("asset_pairs/convert")
    fun convertAssets(@Query("source_asset") sourceAsset: String?,
                      @Query("dest_asset") destAsset: String?,
                      @Query("amount") amount: String?): Call<JsonObject>

    @GET("/accounts/{accountId}/payments")
    fun getPayments(@Path("accountId") accountId: String,
                    @Query("limit") limit: Int,
                    @Query("order") order: String,
                    @Query("cursor") cursor: String,
                    @Query("asset") asset: String? = null,
                    @Query("since") sinceDate: String? = null,
                    @Query("to") toDate: String? = null): Call<Page<PaymentRecord>>

    @GET("/sales")
    fun getSales(@Query("limit") limit: Int,
                 @Query("order") order: String,
                 @Query("cursor") cursor: String,
                 @Query("base_asset") baseAsset: String? = null,
                 @Query("name") name: String? = null,
                 @Query("open_only") openOnly: Boolean = true): Call<Page<Sale>>

    @GET("/sales/{saleId}")
    fun getSale(@Path("saleId") id: Long): Call<Sale>

    @GET("/assets")
    fun getAssetsDetails(): Call<List<Asset>>

    @GET("/assets/{asset}")
    fun getAssetDetails(@Path("asset") asset: String): Call<Asset>

    @POST("/details")
    fun getAccountsDetails(@Body data: HashMap<String, List<String>>)
            : Call<AccountsDetailsResponse>

    @GET("/accounts/{accountId}/offers")
    fun getOffers(@Path("accountId") accountId: String?,
                  @Query("is_buy") isBuy: Boolean,
                  @Query("limit") limit: Int,
                  @Query("order") order: String,
                  @Query("cursor") cursor: String,
                  @Query("order_book_id") saleId: Long?,
                  @Query("quote_asset") quoteAsset: String?,
                  @Query("base_asset") baseAsset: String?):
            Call<Page<Offer>>

    @GET("/charts/{asset}")
    fun getAssetChart(@Path("asset") asset: String): Call<AssetChartData>

    @GET("/users/{accountId}/blobs/{blobId}")
    fun getBlob(@Path("accountId") accountId: String?,
                @Path("blobId") blobId: String?):
            Call<ServerResponse<Blob, ServerError>>

    @GET("/users/{accountId}/blobs")
    fun getBlobs(@Path("accountId") accountId: String?,
                 @Query("fund_id") fundId: Long?,
                 @Query("token_code") asset: String?,
                 @Query("fund_owner") fundOwner: String?,
                 @Query("type") type: Int?):
            Call<ServerResponse<List<Blob>, ServerError>>

    @GET("/accounts/{accountId}/balances/details")
    fun getBalancesDetails(@Path("accountId") accountId: String?):
            Call<List<BalanceDetails>>

    @FormUrlEncoded
    @POST("/transactions")
    fun pushTransaction(@Field("tx") envelopeXdrBase64: String):
            Call<SubmitTransactionResponse>
}