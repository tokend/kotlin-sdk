package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.base.model.DataEntity
import retrofit2.Call
import retrofit2.http.*

interface BlobsService {

    @GET("accounts/{account}/blobs")
    @JvmSuppressWildcards
    fun getAccountOwnedBlobs(@Path("account") accountId: String,
                             @QueryMap query: Map<String, Any>): Call<DataEntity<List<Blob>>>

    @POST("accounts/{account}/blobs")
    fun creteAccountOwnedBlob(@Path("account") accountId: String,
                              @Body blob: DataEntity<Blob>): Call<DataEntity<Blob>>

    @GET("blobs/{blob}")
    fun get(@Path("blobId") blobId: String): Call<DataEntity<Blob>>

    @GET("accounts/{account}/blobs/{blob}")
    fun getAccountOwnedBlob(@Path("account") accountId: String,
                            @Path("blob") blobId: String): Call<DataEntity<Blob>>

    @DELETE("blobs/{blob}")
    fun delete(@Path("blob") blobId: String): Call<Void>

    @POST("blobs")
    fun create(@Body blob: DataEntity<Blob>): Call<DataEntity<Blob>>
}