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
}