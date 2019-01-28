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
}