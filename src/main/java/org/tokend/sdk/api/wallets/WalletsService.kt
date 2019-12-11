package org.tokend.sdk.api.wallets

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.wallets.model.VerifyWalletRequestBody
import org.tokend.sdk.api.wallets.model.WalletResourceBody
import org.tokend.sdk.keyserver.models.LoginParams
import org.tokend.sdk.keyserver.models.WalletData
import retrofit2.Call
import retrofit2.http.*

interface WalletsService {
    @POST("api/wallets/{walletId}/verification")
    fun requestVerification(@Path("walletId") walletId: String): Call<Void>

    @PUT("api/wallets/{walletId}/verification")
    @JvmSuppressWildcards
    fun verify(@Path("walletId") walletId: String,
               @Body data: DataEntity<AttributesEntity<VerifyWalletRequestBody>>):
            Call<Void>

    @GET("api/wallets/kdf")
    fun getLoginParams(@Query("email") login: String? = "",
                       @Query("is_recovery") isRecovery: Boolean = false)
            : Call<DataEntity<LoginParams>>

    @GET("api/wallets/{walletId}")
    fun getById(@Path("walletId") walletId: String): Call<DataEntity<WalletData>>

    @PUT("api/wallets/{walletId}")
    @JvmSuppressWildcards
    fun update(@Path("walletId") walletId: String,
               @Body wallet: WalletResourceBody): Call<Void>

    @POST("api/wallets")
    @JvmSuppressWildcards
    fun create(@Body wallet: WalletResourceBody): Call<DataEntity<WalletData>>
}