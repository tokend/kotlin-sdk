package org.tokend.sdk.api.identity

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.api.identity.model.MassEmailAccountKey
import org.tokend.sdk.api.identity.model.SetPhoneRequestAttributes
import org.tokend.sdk.api.identity.model.SetTelegramRequestAttributes
import org.tokend.sdk.api.identity.model.IdentitySettingsResource
import retrofit2.Call
import retrofit2.http.*

interface IdentitiesService {
    @GET("identities")
    @JvmSuppressWildcards
    fun get(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<IdentityResource>>>

    @POST("identities/mass-emails")
    @JvmSuppressWildcards
    fun getByAccountIds(@Body body: DataEntity<List<MassEmailAccountKey>>):
            Call<JSONAPIDocument<List<IdentityResource>>>

    @PUT("identities/{accountId}/settings/phone")
    @JvmSuppressWildcards
    fun setPhone(@Path("accountId") accountId: String,
                 @Body body: DataEntity<AttributesEntity<SetPhoneRequestAttributes>>): Call<Void>

    @PUT("identities/{accountId}/settings/telegram")
    @JvmSuppressWildcards
    fun setTelegramUsername(@Path("accountId") accountId: String,
                            @Body body: DataEntity<AttributesEntity<SetTelegramRequestAttributes>>): Call<Void>

    @GET("identities/{accountId}/settings")
    @JvmSuppressWildcards
    fun getSettings(@Path("accountId") accountId: String,
                    @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<IdentitySettingsResource>>>

    @GET("identities/{accountId}/settings/{key}")
    @JvmSuppressWildcards
    fun getSettingsItemByKey(@Path("accountId") accountId: String,
                             @Path("key") key: String): Call<JSONAPIDocument<IdentitySettingsResource>>

    @PUT("identities/{accountId}/settings")
    @JvmSuppressWildcards
    fun setSettingsItem(@Path("accountId") accountId: String,
                        @Body data: DataEntity<AttributesEntity<Any>>): Call<Void>
}