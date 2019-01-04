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
}