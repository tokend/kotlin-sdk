package org.tokend.sdk.api.v2.requests.params

import org.tokend.sdk.api.base.params.PagingParamsHolder
import org.tokend.sdk.api.base.params.PagingParamsV2
import org.tokend.sdk.api.requests.model.base.RequestState
import org.tokend.wallet.xdr.ReviewableRequestType

open class RequestsPageParamsV2(
        val reviewer: String? = null,
        val requestor: String? = null,
        val state: RequestState? = null,
        val type: ReviewableRequestType? = null,
        val updatedAfter: Long? = null,
        includes: Collection<String>? = null,
        val pagingParams: PagingParamsV2? = null
) : RequestParamsV2(includes), PagingParamsHolder {
    override val order = pagingParams?.order
    override val cursor = pagingParams?.cursor
    override val limit = pagingParams?.limit

    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            reviewer?.also { put("reviewer", it) }
            requestor?.also { put("requestor", it) }
            state?.also { put("state_i", it.i) }
            type?.also { put("type", it.value) }
            updatedAfter?.also { put("updated_after", it) }
            pagingParams?.also { putAll(it.map()) }
        }
    }
}