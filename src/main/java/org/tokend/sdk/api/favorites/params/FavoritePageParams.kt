package org.tokend.sdk.api.favorites.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

class FavoritePageParams(
        val type: Int? = null,
        include: Collection<String>? = null,
        pagingParams: PagingParamsV2? = null
) : PageQueryParams(pagingParams, include) {

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            type?.also { putFilter("type", it) }
        }
    }

    class Builder: PageQueryParams.Builder() {
        private var type: Int? = null

        fun withType(type: Int) = also { this.type = type }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also {
            super.withPagingParams(pagingParams)
        }

        override fun withInclude(include: Collection<String>?) = also {
            super.withInclude(include)
        }

        override fun withInclude(vararg include: String) = also {
            super.withInclude(*include)
        }

        override fun build(): FavoritePageParams {
            return FavoritePageParams(type, include, pagingParams)
        }
    }
}