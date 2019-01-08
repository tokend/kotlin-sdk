package org.tokend.sdk.api.v2.requests.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see RequestParamsV2.Includes
 */
open class RequestParamsV2(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val REQUEST_DETAILS = "request_details"
            const val REQUESTOR = "requestor"
            const val REVIEWER = "reviewer"
        }
    }
}