package org.tokend.sdk.api.users

import org.tokend.sdk.api.requests.AttributesEntity
import org.tokend.sdk.api.requests.DataEntity
import org.tokend.sdk.api.requests.models.CreateUserRequestBody
import org.tokend.sdk.api.users.model.SimpleUser
import org.tokend.sdk.api.users.model.UserAttributes
import retrofit2.Call
import retrofit2.http.*

interface UsersService {
    @PUT("users/{accountId}")
    @JvmSuppressWildcards
    fun createUser(@Path("accountId") accountId: String?,
                   @Body data: DataEntity<AttributesEntity<CreateUserRequestBody>>):
            Call<Void>

    @GET("users/{accountId}")
    fun getUserInfo(@Path("accountId") accountId: String?):
            Call<DataEntity<SimpleUser>>

    @PATCH("users/{accountId}")
    @JvmSuppressWildcards
    fun patchUser(@Path("accountId") accountId: String?,
                  @Body data: DataEntity<AttributesEntity<UserAttributes>>):
            Call<Void>
}