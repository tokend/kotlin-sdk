package org.tokend.sdk.api.v2.base

import org.tokend.sdk.api.base.params.QueryParams

open class JsonApiQueryParams(
        val include: Collection<String>?
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            include?.also { put("include", it.joinToString(",")) }
        }
    }

    open class Builder {
        protected var include: Collection<String>? = null

        open fun withInclude(include: Collection<String>?) = also { this.include = include }

        open fun withInclude(vararg include: String) = also { this.include = include.toList() }

        open fun build(): JsonApiQueryParams {
            return JsonApiQueryParams(include)
        }
    }

    /**
     * Will put given [value] with key "`filter[key]`", i.e. "`filter[price]`"
     */
    protected fun MutableMap<String, Any>.putFilter(key: String, value: Any) =
            this.putQueryParam("filter", key, value)

    /**
     * Will put given [value] with key "`type[key]`", i.e. "`filter[price]`"
     */
    protected fun MutableMap<String, Any>.putQueryParam(type: String, key: String, value: Any) =
            this.put("$type[$key]", value)
}