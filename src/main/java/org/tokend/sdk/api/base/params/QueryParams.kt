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