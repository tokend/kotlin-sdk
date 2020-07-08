package org.tokend.sdk.api.base.params

/**
 * Paging params for JSONAPI cursor- or number-based paged collections.
 *
 * @see withCursor
 * @see withNumber
 * @see Builder
 */
class PagingParamsV3
private constructor(
        order: PagingOrder?,
        page: String?,
        limit: Int?,
        val numberInsteadOfCursor: Boolean
) : PagingParamsV2(order, limit, page) {
    override fun map(): Map<String, Any> = mutableMapOf<String, Any>().apply {
        cursor?.also {
            if (numberInsteadOfCursor)
                put(QUERY_PARAM_PAGE_NUMBER, it)
            else
                put(QUERY_PARAM_PAGE_CURSOR, it)
        }
        limit?.also { put(QUERY_PARAM_LIMIT, it) }
        order?.also { put(QUERY_PARAM_ORDER, it) }
    }

    class Builder {
        private var order: PagingOrder? = null
        private var limit: Int? = null
        private var page: String? = null
        private var numberInsteadOfCursor: Boolean = false

        fun withOrder(order: PagingOrder?) = also { this.order = order }

        fun withLimit(limit: Int?) = also { this.limit = limit }

        fun withPage(page: String?) = also { this.page = page }

        fun withPage(page: Int?) = also { this.page = page?.toString() }

        fun withNumberInsteadOfCursor() = also { this.numberInsteadOfCursor = true }

        fun build(): PagingParamsV3 {
            return PagingParamsV3(order, page, limit, numberInsteadOfCursor)
        }
    }

    companion object {
        @JvmStatic
        @JvmOverloads
        fun withCursor(cursor: String? = null,
                       limit: Int? = null,
                       order: PagingOrder? = null
        ) = PagingParamsV3(order, cursor, limit, numberInsteadOfCursor = false)

        @JvmStatic
        @JvmOverloads
        fun withNumber(number: String? = null,
                       limit: Int? = null,
                       order: PagingOrder? = null
        ) = PagingParamsV3(order, number, limit, numberInsteadOfCursor = false)
    }
}