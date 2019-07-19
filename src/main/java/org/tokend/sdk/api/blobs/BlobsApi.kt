package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.base.SimpleRetrofitApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.base.params.map
import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.blobs.model.BlobCreationRequestBody
import org.tokend.sdk.api.blobs.params.BlobPageParams

open class BlobsApi(
        protected val blobsService: BlobsService
) {

    /**
     * @return list of blobs owned by specific account.
     *
     * @see getBlob
     */
    @Deprecated("Use BlobsApi.getBlob instead")
    fun getAccountOwnedBlobs(accountId: String, params: BlobPageParams? = null): ApiRequest<List<Blob>> {
        return MappedRetrofitApiRequest(
                blobsService.getAccountOwnedBlobs(accountId, params.map()),
                { it.data }
        )
    }

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
     * @return specific blob owned by specific account.
     *
     * @see getBlob
     */
    @Deprecated("Use BlobsApi.getBlob instead")
    fun getAccountOwnedBlob(accountId: String,
                            blobId: String): ApiRequest<Blob> {
        return MappedRetrofitApiRequest(
                blobsService.getAccountOwnedBlob(accountId, blobId),
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