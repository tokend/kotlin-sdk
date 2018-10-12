package org.tokend.sdk.keyserver

import org.tokend.sdk.api.models.WalletData
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.keyserver.models.LoginParamsResponse
import retrofit2.Call
import retrofit2.http.*

interface KeyServerApi {
    /**
     * Returns wallet login parameters.
     * If login is an empty string - returns an actual network login params
     * without salt.
     */
    @GET("wallets/kdf")
    fun getLoginParams(@Query("email") login: String? = "",
                       @Query("is_recovery") isRecovery: Boolean = false)
            : Call<DataEntity<LoginParamsResponse>>

    @GET("wallets/{walletId}")
    fun getWalletData(@Path("walletId") walletId: String)
            : Call<DataEntity<WalletData>>

    @PUT("wallets/{walletId}")
    @JvmSuppressWildcards
    fun updateWallet(@Path("walletId") walletId: String?,
                     @Body data: DataEntity<WalletData>):
            Call<Void>

    @POST("wallets")
    @JvmSuppressWildcards
    fun createWallet(@Body data: DataEntity<WalletData>):
            Call<Void>
}