package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState

/**
 * Query params for limits update reviewable requests request.
 * @see <a href="https://tokend.gitlab.io/docs/#get-limits-updates-requests">Docs</a>
 */
class LimitsUpdateRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null,
        val documentHash: String? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            documentHash?.also { put("document_hash", it) }
        }
    }
}