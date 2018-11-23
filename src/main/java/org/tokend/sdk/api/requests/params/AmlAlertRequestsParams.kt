package org.tokend.sdk.api.requests.params

import org.tokend.sdk.api.base.params.PagingParams
import org.tokend.sdk.api.requests.model.base.RequestState

/**
 * Query params for asset-related reviewable requests request.
 * @see <a href="https://tokend.gitlab.io/docs/#get-aml-alerts-requests">Docs</a>
 */
class AmlAlertRequestsParams(
        pagingParams: PagingParams? = null,
        reviewer: String? = null,
        requestor: String? = null,
        state: RequestState? = null,
        updatedAfterTimestamp: Long? = null
) : RequestsParams(pagingParams, reviewer, requestor, state, updatedAfterTimestamp)