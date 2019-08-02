package org.tokend.sdk.api.identity

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.model.AttributesEntity
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.api.identity.model.MassEmailAccountKey
import org.tokend.sdk.api.identity.model.SetPhoneRequestAttributes
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
}