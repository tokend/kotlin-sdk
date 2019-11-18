package org.tokend.sdk.api.integrations.dns.params

import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.v3.base.PageQueryParams

/**
 * @see ClientsPageParams.Includes
 */
class ClientsPageParams(
        private val sort: String? = null,
        private val search: String? = null,
        pagingParams: PagingParamsV2? = null,
        include: Collection<String>? = null
) : PageQueryParams(pagingParams, include) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            sort?.also { put("sort", it) }
            search?.also { put("search", it) }
        }
    }

    open class Builder: PageQueryParams.Builder() {
        protected open var sort: kotlin.String? = null
        protected open var search: kotlin.String? = null

        open fun withSort(sort: kotlin.String) = also { this.sort = sort }

        open fun withSearch(search: kotlin.String) = also { this.search = search }

        override fun withInclude(include: Collection<String>?) = also { super.withInclude(include) }

        override fun withInclude(vararg include: String) = also { super.withInclude(*include) }

        override fun withPagingParams(pagingParams: PagingParamsV2) = also { super.withPagingParams(pagingParams) }

        override fun build(): ClientsPageParams =
                ClientsPageParams(sort, search, pagingParams, include)
    }

    class Includes {
        companion object {
            const val BALANCES = "balances"
        }
    }
}