package org.tokend.sdk.api.authenticator

import org.tokend.sdk.api.authenticator.model.AuthResult
import org.tokend.sdk.api.base.model.DataEntity
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthResultsService {
    @GET("authresult/{publicKey}")
    fun getAuthResult(@Path("publicKey") publicKey: String): Call<DataEntity<AuthResult>>

    @POST("authresult/{publicKey}")
    fun postAuthResult(@Path("publicKey") publicKey: String,
                       @Body resultData: DataEntity<AuthResult>): Call<Void>
}