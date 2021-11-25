package org.tokend.sdk.api.v3.requests

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v3.model.generated.resources.ReviewableRequestResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface RequestsServiceV3 {
    @GET("v3/requests")
    @JvmSuppressWildcards
    fun getRequests(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ReviewableRequestResource>>>

    @GET("v3/requests/{id}")
    @JvmSuppressWildcards
    fun getRequestById(@Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<ReviewableRequestResource>>

    @GET("v3/accounts/{requestor}/requests/{id}")
    @JvmSuppressWildcards
    fun getRequestById(@Path("requestor") requestor: String,
                       @Path("id") id: String,
                       @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<ReviewableRequestResource>>

    @GET("v3/change_role_requests")
    @JvmSuppressWildcards
    fun getChangeRoleRequests(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ReviewableRequestResource>>>

    @GET("v3/create_asset_requests")
    @JvmSuppressWildcards
    fun getAssetCreateRequests(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ReviewableRequestResource>>>

    @GET("v3/asset_update_requests\n")
    @JvmSuppressWildcards
    fun getAssetUpdateRequests(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<ReviewableRequestResource>>>
}