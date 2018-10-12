package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.blobs.params.BlobsParams
import org.tokend.sdk.api.models.Blob

open class BlobsApi(
        protected val blobsService: BlobsService
) {
    fun get(accountId: String,
            params: BlobsParams? = null): ApiRequest<List<Blob>> {
        return MappedRetrofitApiRequest(
                blobsService.getBlobs(
                        accountId,
                        params?.map()
                ),
                { it.data }
        )
    }

    fun getById(accountId: String,
                blobId: String): ApiRequest<Blob> {
        return MappedRetrofitApiRequest(
                blobsService.getBlob(accountId, blobId),
                { it.data }
        )
    }
}