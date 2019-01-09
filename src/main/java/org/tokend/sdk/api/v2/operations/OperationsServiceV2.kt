package org.tokend.sdk.api.v2.operations

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.v2.operations.model.OperationResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface OperationsServiceV2 {
    @GET("operations")
    @JvmSuppressWildcards
    fun getOperations(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<OperationResource>>>

    @GET("operations/{id}")
    @JvmSuppressWildcards
    fun getOperationById(@Path("id") id: String,
                         @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<OperationResource>>
}