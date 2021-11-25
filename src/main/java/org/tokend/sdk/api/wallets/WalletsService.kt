package org.tokend.sdk.api.wallets

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.keyserver.models.Wallet
import org.tokend.sdk.keyserver.models.LoginParams
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
    ): Call<Wallet>

    @PUT("wallets/{walletId}")
    @JvmSuppressWildcards
    fun update(
        @Path("walletId") walletId: String,
        @Body body: Any
    ): Call<Void>

    @POST("wallets")
    @JvmSuppressWildcards
    fun create(@Body body: Any): Call<Wallet>
}