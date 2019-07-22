package org.tokend.sdk.api.identity

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.identity.model.IdentityResource
import org.tokend.sdk.api.identity.model.MassEmailAccountKey
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface IdentitiesService {
    @GET("identities")
    @JvmSuppressWildcards
    fun get(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<IdentityResource>>>

    @POST("identities/mass-emails")
    @JvmSuppressWildcards
    fun getByAccountIds(@Body body: DataEntity<List<MassEmailAccountKey>>):
            Call<JSONAPIDocument<List<IdentityResource>>>
}