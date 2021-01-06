package org.tokend.sdk.api.base.params

/**
 * Parameters transformer for Query map.
 */
interface QueryParams {
    fun map(): Map<String, Any>
}

fun QueryParams?.map(): Map<String, Any> {
    return this?.map() ?: emptyMap()
}

class QueryParamsBuilder {
    private val map = mutableMapOf<String, Any>()

    fun filter(key: String, value: Any?) =
            param("filter[$key]", value)

    fun param(key: String, value: Any?) = apply {
        if (value != null) {
            map[key] = value
        }
    }

    fun append(extra: QueryParams) = apply {
        map.putAll(extra.map())
    }

    fun build(): QueryParams {
        return object : QueryParams {
            override fun map() = this@QueryParamsBuilder.map
        }
    }

    fun map(): Map<String, Any> = map
}