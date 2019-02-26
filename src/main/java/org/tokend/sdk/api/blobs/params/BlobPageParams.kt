package org.tokend.sdk.api.blobs.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.blobs.model.BlobType
import org.tokend.sdk.api.v3.base.PageQueryParams

open class BlobPageParams(
        val type: BlobType? = null,
        val isDeleted: Boolean? = null,
        pagingParamsV2: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParamsV2, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
                    type?.also { putFilter("type", it.value) }
                    isDeleted?.also { putFilter("deleted", it) }
                }
    }

    class Builder: PageQueryParams.Builder() {
        private var type: BlobType? = null
        private var isDeleted: Boolean? = null

        fun withType(type: BlobType) = also { this.type = type }

        fun withIsDeleted(isDeleted: Boolean) = also { this.isDeleted = isDeleted }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): BlobPageParams {
            return BlobPageParams(type, isDeleted, pagingParams, include)
        }
    }
}
