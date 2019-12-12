package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.blobs.model.BlobCreationRequestBody
import retrofit2.Call
import retrofit2.http.*

interface BlobsService {
    @POST("api/accounts/{account}/blobs")
    @JvmSuppressWildcards
    fun creteAccountOwnedBlob(@Path("account") accountId: String,
                              @Body blob: DataEntity<Blob>): Call<DataEntity<Blob>>

    @GET("api/blobs/{blobId}")
    fun get(@Path("blobId") blobId: String): Call<DataEntity<Blob>>

    @DELETE("api/blobs/{blob}")
    fun delete(@Path("blob") blobId: String): Call<Void>

    @POST("api/blobs")
    @JvmSuppressWildcards
    fun create(@Body blob: DataEntity<BlobCreationRequestBody>): Call<DataEntity<Blob>>
}