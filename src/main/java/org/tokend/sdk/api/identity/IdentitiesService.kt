package org.tokend.sdk.api.identity

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.identity.model.IdentityResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface IdentitiesService {
    @GET("identities")
    @JvmSuppressWildcards
    fun get(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<IdentityResource>>>
}