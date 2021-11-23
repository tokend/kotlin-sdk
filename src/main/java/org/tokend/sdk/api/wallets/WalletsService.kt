package org.tokend.sdk.api.wallets

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.wallets.model.WalletCreationRequestBody
import org.tokend.sdk.keyserver.models.ExistingWallet
import org.tokend.sdk.keyserver.models.LoginParams
import org.tokend.sdk.keyserver.models.WalletCreationData
import retrofit2.Call
import retrofit2.http.*

interface WalletsService {
    @POST("wallets/{walletId}/verification")
    fun requestVerification(@Path("walletId") walletId: String): Call<Void>

    @PUT("wallets/{walletId}/verification")
    @JvmSuppressWildcards
    fun verify(
        @Path("walletId") walletId: String,
        @Body data: DataEntity<AttributesEntity<Any>>
    ): Call<Void>

    @GET("wallets/kdf")
    fun getLoginParams(
        @Query("email") login: String? = "",
        @Query("is_recovery") isRecovery: Boolean = false
    ): Call<DataEntity<LoginParams>>

    @GET("wallets/{walletId}")
    @JvmSuppressWildcards
    fun getById(
        @Path("walletId") walletId: String,
        @QueryMap queryMap: Map<String, Any>?
    ): Call<ExistingWallet>

    @PUT("wallets/{walletId}")
    @JvmSuppressWildcards
    fun update(
        @Path("walletId") walletId: String,
        @Body wallet: WalletCreationRequestBody
    ): Call<Void>

    @POST("wallets")
    @JvmSuppressWildcards
    fun create(@Body wallet: WalletCreationRequestBody): Call<ExistingWallet>
}