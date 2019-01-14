package org.tokend.sdk.api.tfa

import com.google.gson.JsonObject
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.tfa.model.CreateTfaRequestBody
import org.tokend.sdk.api.tfa.model.TfaFactor
import retrofit2.Call
import retrofit2.http.*

interface TfaService {
    @GET("wallets/{walletId}/factors")
    fun getFactors(@Path("walletId") walletId: String?): Call<DataEntity<List<TfaFactor>>>

    @POST("wallets/{walletId}/factors")
    @JvmSuppressWildcards
    fun createFactor(@Path("walletId") walletId: String?,
                     @Body data: DataEntity<CreateTfaRequestBody>): Call<DataEntity<JsonObject>>

    @DELETE("wallets/{walletId}/factors/{id}")
    fun deleteFactor(@Path("walletId") walletId: String?,
                     @Path("id") factorId: Long?): Call<Void>

    @PATCH("wallets/{walletId}/factors/{id}")
    @JvmSuppressWildcards
    fun updateFactor(@Path("walletId") walletId: String?,
                     @Path("id") factorId: Long?,
                     @Body data: DataEntity<AttributesEntity<TfaFactor.Attributes>>): Call<Void>
}