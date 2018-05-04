package org.tokend.sdk.api.tfa

import org.tokend.sdk.api.requests.DataEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface TfaVerificationService {
    @PUT("/wallets/{walletId}/factors/{id}/verification")
    fun verifyTfaBackend(@Path("walletId") walletId: String,
                         @Path("id") backendId: Long,
                         @Body data: DataEntity<@JvmSuppressWildcards Any>):
            Call<Void>
}