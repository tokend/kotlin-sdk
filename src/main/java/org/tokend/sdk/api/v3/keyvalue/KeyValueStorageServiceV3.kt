package org.tokend.sdk.api.v3.keyvalue

import com.github.jasminb.jsonapi.JSONAPIDocument
import org.tokend.sdk.api.generated.resources.KeyValueEntryResource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface KeyValueStorageServiceV3 {
    @GET("key_value")
    @JvmSuppressWildcards
    fun getEntries(@QueryMap query: Map<String, Any>): Call<JSONAPIDocument<List<KeyValueEntryResource>>>

    @GET("key_value/{id}")
    @JvmSuppressWildcards
    fun getEntryById(@Path("id") id: String,
                     @QueryMap query: Map<String, Any>): Call<JSONAPIDocument<KeyValueEntryResource>>
}