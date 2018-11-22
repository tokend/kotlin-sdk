package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.api.requests.model.base.RequestState

/**
 * Base requests query params holder.
 */
open class RequestsParams(
        val pagingParams: PagingParams? = null,
        val reviewer: String? = null,
        val requestor: String? = null,
        val state: RequestState? = null,
        val updatedAfterTimestamp: Long? = null
) : QueryParams, PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            pagingParams?.also { putAll(it.map()) }
            state?.also { put("state", it) }
            reviewer?.also { put("reviewer", it) }
            requestor?.also { put("requestor", it) }
            updatedAfterTimestamp?.also { put("updated_after", it) }
        }
    }
}