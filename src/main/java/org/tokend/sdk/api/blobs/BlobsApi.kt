package org.tokend.sdk.api.blobs

import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.MappedRetrofitApiRequest
import org.tokend.sdk.api.blobs.params.BlobsParams
import org.tokend.sdk.api.blobs.model.Blob

open class BlobsApi(
        protected val blobsService: BlobsService
) {
    /**
     * Will return list of blobs filtered with given params
     * See <a href="https://tokend.gitlab.io/docs/?http#filter-blobs">Docs</a>
     */
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

    /**
     * Will return specific blob by it's id.
     * See <a href="https://tokend.gitlab.io/docs/?http#get">Docs</a>
     */
    fun getById(accountId: String,
                blobId: String): ApiRequest<Blob> {
        return MappedRetrofitApiRequest(
                blobsService.getBlob(accountId, blobId),
                { it.data }
        )
    }
}