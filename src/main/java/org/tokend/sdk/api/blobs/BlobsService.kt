package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.base.model.DataEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface BlobsService {
    @GET("users/{accountId}/blobs/{blobId}")
    fun getBlob(@Path("accountId") accountId: String?,
                @Path("blobId") blobId: String?): Call<DataEntity<Blob>>

    @GET("users/{accountId}/blobs")
    @JvmSuppressWildcards
    fun getBlobs(@Path("accountId") accountId: String,
                 @QueryMap query: Map<String, Any>?): Call<DataEntity<List<Blob>>>
}