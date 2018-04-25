package org.tokend.sdk.keyserver

import org.tokend.sdk.api.models.WalletData
import org.tokend.sdk.api.responses.ServerError
import org.tokend.sdk.api.responses.ServerResponse
import org.tokend.sdk.keyserver.models.LoginParamsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface KeyServerApi {
    /**
     * Returns wallet login parameters.
     * If login is an empty string - returns an actual network login params
     * without salt.
     */
    @GET("/wallets/kdf")
    fun getLoginParams(@Query("email") login: String? = "",
                       @Query("is_recovery") isRecovery: Boolean = false)
            : Call<ServerResponse<LoginParamsResponse, ServerError>>

    @GET("/wallets/{walletId}")
    fun getWalletData(@Path("walletId") walletId: String)
            : Call<ServerResponse<WalletData, ServerError>>
}