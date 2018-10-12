package org.tokend.sdk.api.blobs.params

import org.tokend.sdk.api.base.params.QueryParams

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