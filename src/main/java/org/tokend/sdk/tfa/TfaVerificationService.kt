package org.tokend.sdk.tfa

import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path

interface TfaVerificationService {
    @PUT("wallets/{walletId}/factors/{id}/verification")
    fun verifyTfaFactor(
        @Path("walletId") walletId: String,
        @Path("id") factorId: Long,
        @Body data: DataEntity<Any>
    ): Call<Void>
}