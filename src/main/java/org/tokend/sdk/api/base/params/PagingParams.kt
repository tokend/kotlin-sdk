package org.tokend.sdk.api.base.params

class PagingParams(
        val order: Order? = null,
        val limit: Int? = null,
        val cursor: Long? = null
) : QueryParams {
    enum class Order {
        ASC, DESC;

        override fun toString(): String {
            return super.toString().toLowerCase()
        }
    }

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>()
                .apply {
                    order?.also { put("order", it) }
                    limit?.also { put("limit", it) }
                    cursor?.also { put("cursor", it) }
                }
    }
}