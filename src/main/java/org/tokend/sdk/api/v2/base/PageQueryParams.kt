package org.tokend.sdk.api.v2.base

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2

open class PageQueryParams(
        val pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : JsonApiQueryParams(include), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            pagingParams?.also { putAll(it.map()) }
        }
    }

    open class Builder : JsonApiQueryParams.Builder() {
        protected var pagingParams: PagingParamsV2? = null

        open fun withPagingParams(pagingParams: PagingParamsV2) = also { this.pagingParams = pagingParams }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): PageQueryParams {
            return PageQueryParams(pagingParams, include)
        }
    }
}