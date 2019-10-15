package org.tokend.sdk.api.custom

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface CustomRequestsService {
    @GET
    @JvmSuppressWildcards
    fun get(@Url url: String,
            @QueryMap query: Map<String, Any>,
            @HeaderMap headers: Map<String, Any>): Call<ResponseBody>

    @POST
    @JvmSuppressWildcards
    fun post(@Url url: String,
             @Body body: Any?,
             @QueryMap query: Map<String, Any>,
             @HeaderMap headers: Map<String, Any>): Call<ResponseBody>

    @PUT
    @JvmSuppressWildcards
    fun put(@Url url: String,
            @Body body: Any?,
            @QueryMap query: Map<String, Any>,
            @HeaderMap headers: Map<String, Any>): Call<ResponseBody>

    @PATCH
    @JvmSuppressWildcards
    fun patch(@Url url: String,
              @Body body: Any?,
              @QueryMap query: Map<String, Any>,
              @HeaderMap headers: Map<String, Any>): Call<ResponseBody>

    @DELETE
    @JvmSuppressWildcards
    fun delete(@Url url: String,
               @QueryMap query: Map<String, Any>,
               @HeaderMap headers: Map<String, Any>): Call<ResponseBody>
}