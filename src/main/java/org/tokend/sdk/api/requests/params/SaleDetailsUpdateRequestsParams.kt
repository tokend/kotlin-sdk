package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState

/**
 * Query params for sale details update reviewable requests request.
 * @see <a href="https://tokend.gitlab.io/docs/#get-update-sale-details-requests">Docs</a>
 */
class SaleDetailsUpdateRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp)