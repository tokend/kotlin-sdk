package org.tokend.sdk.api.base.params

/**
 * Converts common paging params to Query map.
 */
class PagingParams(
        override val order: PagingOrder? = null,
        override val limit: Int? = null,
        override val cursor: String? = null
) : QueryParams, PagingParamsHolder {

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    order?.also { put("order", it) }
                    limit?.also { put("limit", it) }
                    cursor?.also { put("cursor", it) }
                }
    }
}