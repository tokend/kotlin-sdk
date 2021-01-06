package org.tokend.sdk.api.v3.base

import org.tokend.sdk.api.base.params.QueryParams

/**
 * Builds query map for JSONAPI endpoint.
 *
 * You can use [JsonApiQueryMapBuilder.append] to append [PageQueryParams] for example
 */
class JsonApiQueryMapBuilder {
    private val map = mutableMapOf<String, Any>()

    fun filter(key: String, value: Any?) =
            param("filter[$key]", value)

    fun include(vararg resources: String) =
            param("include", resources.joinToString(separator = ","))

    fun param(key: String, value: Any?) = apply {
        if (value != null) {
            map[key] = value
        }
    }

    fun append(extra: QueryParams) =
            append(extra.map())

    fun append(extra: Map<String, Any>) = apply {
        map.putAll(extra)
    }

    fun build(): Map<String, Any> = map
}