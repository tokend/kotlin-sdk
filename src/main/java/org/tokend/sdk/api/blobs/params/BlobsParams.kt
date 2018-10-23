package org.tokend.sdk.api.blobs.params

import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.api.blobs.BlobsApi

/**
 * Need to make a query to get specific blobs.
 * @see BlobsApi.get
 */
open class BlobsParams(
        val typeMask: Int? = null,
        val filters: Map<String, Any>? = null
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    typeMask?.also { put("type", it) }
                    filters?.also { putAll(it) }
                }
    }
}