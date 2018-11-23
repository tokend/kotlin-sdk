package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState

/**
 * Query params for issuance reviewable requests request.
 * @see <a href="https://tokend.gitlab.io/docs/#get-issuance-requests">Docs</a>
 */
class IssuanceRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null,
        val asset: String? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            asset?.also { put("asset", it) }
        }
    }
}