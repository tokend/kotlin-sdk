package org.tokend.sdk.api.blobs

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import org.tokend.sdk.api.base.ApiRequest
import org.tokend.sdk.api.base.model.DataEntity
import org.tokend.sdk.api.blobs.model.Blob
import org.tokend.sdk.api.blobs.model.BlobResponseData
import org.tokend.sdk.api.custom.CustomRequestsApi

open class BlobsApi(
    protected val customRequestsApi: CustomRequestsApi
) {
    /**
     * @return specific blob by its id.
     */
    fun getBlob(blobId: String): ApiRequest<Blob> {
        return customRequestsApi.get(
            url = "blobs/$blobId",
            responseType = jacksonTypeRef<DataEntity<BlobResponseData>>()
        )
            .map { it.data.toBlob() }
    }

    /**
     * Deletes the specific blob by its id.
     */
    fun delete(blobId: String): ApiRequest<Void> {
        return customRequestsApi.delete(
            url = "blobs/$blobId",
            responseClass = Void::class.java
        )
    }


    /**
     * @return created blob with generated id.
     */
    @JvmSuppressWildcards
    fun create(
        blob: Blob,
        ownerAccountId: String
    ): ApiRequest<Blob> {
        return customRequestsApi.post(
            url = "blobs",
            body = DataEntity(
                mapOf(
                    "type" to blob.typeName,
                    "attributes" to mapOf(
                        "value" to blob.valueString
                    ),
                    "relationships" to mapOf(
                        "owner" to DataEntity(
                            mapOf(
                                "id" to ownerAccountId
                            )
                        )
                    )
                )
            ),
            responseType = jacksonTypeRef<DataEntity<BlobResponseData>>()
        )
            .map { it.data.toBlob() }
    }
}