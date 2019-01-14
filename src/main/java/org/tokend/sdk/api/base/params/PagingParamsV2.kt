package org.tokend.sdk.api.base.params

/**
 * Paging params for requests with pagination
 * based on 'page' param instead of 'cursor'.
 */
class PagingParamsV2
@JvmOverloads
constructor(
        order: PagingOrder? = null,
        limit: Int? = null,
        page: String? = null
) : PagingParams(order, limit, page) {
    override fun map(): Map<String, Any> {
        return super.map()
                .toMutableMap()
                .apply {
                    cursor?.also { put("page", it) }
                }
    }

    class Builder {
        private var order: PagingOrder? = null
        private var limit: Int? = null
        private var page: String? = null

        fun withOrder(order: PagingOrder?) = also { this.order = order }

        fun withLimit(limit: Int?) = also { this.limit = limit }

        fun withPage(page: String?) = also { this.page = page }

        fun withPage(page: Int?) = also { this.page = page?.toString() }

        fun build(): PagingParamsV2 {
            return PagingParamsV2(order, limit, page)
        }
    }
}