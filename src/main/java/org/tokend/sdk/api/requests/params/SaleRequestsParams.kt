package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState

class SaleRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null,
        val baseAsset: String? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            baseAsset?.also { put("base_asset", it) }
        }
    }
}