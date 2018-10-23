package org.tokend.sdk.api.base.params

/**
 * Parameters transformer for Query map.
 */
interface QueryParams {
    fun map(): Map<String, Any>
}