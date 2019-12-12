package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.blobs.model.BlobCreationRequestBody

open class BlobsApi(
        protected val blobsService: BlobsService
) {
    /**
     * Will create blob for specific account.
     *
     * @see create
     */
    @Deprecated("Use BlobsApi.create instead")
    fun createAccountOwnedBlob(accountId: String, blob: Blob): ApiRequest<Blob> {
        return MappedRetrofitApiRequest(
                blobsService.creteAccountOwnedBlob(accountId, DataEntity(blob)),
                { it.data }
        )
    }

    /**
     * @return specific blob by it's id.
     */
    fun getBlob(blobId: String): ApiRequest<Blob> {
        return MappedRetrofitApiRequest(
                blobsService.get(blobId),
                { it.data }
        )
    }

    /**
     * Will delete specific blob.
     */
    fun delete(blobId: String): ApiRequest<Void> {
        return SimpleRetrofitApiRequest(
                blobsService.delete(blobId)
        )
    }


    /**
     * Will create blob.
     */
    @JvmSuppressWildcards
    fun create(blob: Blob,
               ownerAccountId: String? = null): ApiRequest<Blob> {
        return MappedRetrofitApiRequest(
                blobsService.create(DataEntity(BlobCreationRequestBody(
                        blob,
                        ownerAccountId
                ))),
                { it.data }
        )
    }
}