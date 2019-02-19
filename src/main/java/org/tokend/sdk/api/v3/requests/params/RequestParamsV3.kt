package org.tokend.sdk.api.v3.requests.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see RequestParamsV3.Includes
 */
open class RequestParamsV3(
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