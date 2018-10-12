package org.tokend.sdk.api

import org.tokend.sdk.api.accounts.model.AccountResponse
import org.tokend.sdk.api.accounts.model.AccountsDetailsResponse
import org.tokend.sdk.api.assets.model.AssetChartData
import org.tokend.sdk.api.assets.model.AssetPair
import org.tokend.sdk.api.assets.model.ConvertAssetsResponse
import org.tokend.sdk.api.assets.model.SimpleAsset
import org.tokend.sdk.api.fees.model.Fee
import org.tokend.sdk.api.models.*
import org.tokend.sdk.api.sales.model.SimpleSale
import org.tokend.sdk.api.requests.AttributesEntity
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.api.requests.models.*
import org.tokend.sdk.api.responses.*
import org.tokend.sdk.api.tfa.model.TfaFactor
import org.tokend.sdk.api.transactions.model.SubmitTransactionResponse
import org.tokend.sdk.api.users.model.SimpleUser
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET(".")
    fun getSystemInfo(): Call<SystemInfo>

    @GET("accounts/{accountId}")
    fun getAccount(@Path("accountId") accountId: String?):
            Call<AccountResponse>

    /**
     * Will return empty account with only 'signers' filled.
     */
    @GET("accounts/{accountId}/signers")
    fun getAccountSigners(@Path("accountId") accountId: String?):
            Call<AccountResponse>

    @POST("wallets/{walletId}/verification")
    fun requestVerificationLink(@Path("walletId") walletId: String?):
            Call<Void>

    @PUT("wallets/{walletId}/verification")
    @JvmSuppressWildcards
    fun verifyWallet(@Path("walletId") walletId: String?,
                     @Body data: DataEntity<AttributesEntity<VerifyWalletRequestBody>>):
            Call<Void>

    @PUT("users/{accountId}")
    @JvmSuppressWildcards
    fun createUser(@Path("accountId") accountId: String?,
                   @Body data: DataEntity<AttributesEntity<CreateUserRequestBody>>):
            Call<Void>

    @GET("users/{accountId}")
    fun getUserInfo(@Path("accountId") accountId: String?):
            Call<ServerResponse<SimpleUser, ServerError>>

    @PATCH("users/{accountId}")
    @JvmSuppressWildcards
    fun patchUser(@Path("accountId") accountId: String?,
                  @Body data: DataEntity<AttributesEntity<PatchUserRequestBody>>):
            Call<Void>

    //region TFA
    @GET("wallets/{walletId}/factors")
    fun getTfaBackends(@Path("walletId") walletId: String?):
            Call<ServerResponse<MutableList<TfaFactor>, ServerError>>

    @POST("wallets/{walletId}/factors")
    @JvmSuppressWildcards
    fun createTfaBackend(@Path("walletId") walletId: String?,
                         @Body data: DataEntity<CreateTfaRequestBody>):
            Call<ServerResponse<TfaFactor, ServerError>>

    @DELETE("wallets/{walletId}/factors/{id}")
    fun deleteTfaBackend(@Path("walletId") walletId: String?,
                         @Path("id") backendId: Long?): Call<Void>

    @PATCH("wallets/{walletId}/factors/{id}")
    @JvmSuppressWildcards
    fun updateTfaBackend(@Path("walletId") walletId: String?,
                         @Path("id") backendId: Long?,
                         @Body data: DataEntity<UpdateTfaRequestBody>): Call<Void>
    //endregion TFA

    @GET("fees/{feeType}")
    fun getFee(@Path("feeType") feeType: Int,
               @Query("account") accountId: String,
               @Query("asset") asset: String,
               @Query("amount") amount: String?,
               @Query("subtype") subtype: Int): Call<Fee>

    @GET("user_id")
    fun getAccountIdByEmail(@Query("email") email: String): Call<AccountIdResponse>

    @GET("accounts/{accountId}/balances")
    fun getAccountBalances(@Path("accountId") accountId: String):
            Call<List<AccountResponse.Balance>>

    @GET("asset_pairs")
    fun getAssetPairs(): Call<List<AssetPair>>

    @GET("asset_pairs/convert")
    fun convertAssets(@Query("source_asset") sourceAsset: String?,
                      @Query("dest_asset") destAsset: String?,
                      @Query("amount") amount: String?): Call<ConvertAssetsResponse>

    @GET("accounts/{accountId}/payments")
    fun getPayments(@Path("accountId") accountId: String,
                    @Query("limit") limit: Int = 25,
                    @Query("order") order: String = "desc",
                    @Query("cursor") cursor: String? = null,
                    @Query("asset") asset: String? = null,
                    @Query("since") sinceDate: String? = null,
                    @Query("to") toDate: String? = null,
                    @Query("completed_only") completedOnly: Boolean? = null,
                    @Query("pending_only") pendingOnly: Boolean? = null): Call<Page<PaymentRecord>>

    @GET("sales")
    fun getSales(@Query("limit") limit: Int,
                 @Query("order") order: String,
                 @Query("cursor") cursor: String,
                 @Query("base_asset") baseAsset: String? = null,
                 @Query("name") name: String? = null,
                 @Query("open_only") openOnly: Boolean = true): Call<Page<SimpleSale>>

    @GET("sales/{saleId}")
    fun getSale(@Path("saleId") id: Long): Call<SimpleSale>

    @GET("assets")
    fun getAssetsDetails(): Call<List<SimpleAsset>>

    @GET("assets/{asset}")
    fun getAssetDetails(@Path("asset") asset: String): Call<SimpleAsset>

    @POST("details")
    fun getAccountsDetails(@Body body: AccountsDetailsRequestBody)
            : Call<AccountsDetailsResponse>

    @GET("accounts/{accountId}/offers")
    fun getOffers(@Path("accountId") accountId: String?,
                  @Query("is_buy") isBuy: Boolean?,
                  @Query("limit") limit: Int,
                  @Query("order") order: String,
                  @Query("cursor") cursor: String,
                  @Query("only_primary") onlyPrimary: Boolean,
                  @Query("order_book_id") orderBookId: Long?,
                  @Query("quote_asset") quoteAsset: String?,
                  @Query("base_asset") baseAsset: String?):
            Call<Page<Offer>>

    @GET("charts/{asset}")
    fun getAssetChart(@Path("asset") asset: String): Call<AssetChartData>

    @GET("users/{accountId}/blobs/{blobId}")
    fun getBlob(@Path("accountId") accountId: String?,
                @Path("blobId") blobId: String?):
            Call<ServerResponse<Blob, ServerError>>

    @GET("users/{accountId}/blobs")
    @JvmSuppressWildcards
    fun getBlobs(@Path("accountId") accountId: String,
                 @Query("type") type: Int? = null,
                 @QueryMap filters: Map<String, Any>? = null):
            Call<ServerResponse<List<Blob>, ServerError>>

    @GET("accounts/{accountId}/balances/details")
    fun getBalancesDetails(@Path("accountId") accountId: String?):
            Call<List<SimpleBalanceDetails>>

    @FormUrlEncoded
    @POST("transactions")
    fun pushTransaction(@Field("tx") envelopeXdrBase64: String):
            Call<SubmitTransactionResponse>

    @GET("order_book")
    fun getOrderBook(@Query("is_buy") isBuy: Boolean,
                     @Query("base_asset") baseAsset: String,
                     @Query("quote_asset") quoteAsset: String,
                     @Query("cursor") cursor: String,
                     @Query("limit") limit: Int): Call<Page<Offer>>

    @POST("users/{accountId}/favorites")
    fun addToFavorites(@Path("accountId") accountId: String,
                       @Body entry: FavoriteEntry): Call<Void>

    @DELETE("users/{accountId}/favorites/{entryId}")
    fun removeFromFavorites(@Path("accountId") accountId: String,
                            @Path("entryId") id: Long): Call<Void>

    @GET("users/{accountId}/favorites")
    fun getFavorites(@Path("accountId") accountId: String):
            Call<ServerResponse<List<FavoriteEntry>, ServerError>>

    @GET("sale_antes")
    fun getSaleAntes(@Query("participant_balance_id") balanceId: String,
                     @Query("sale_id") saleId: Long): Call<Page<SaleAnte>>
}