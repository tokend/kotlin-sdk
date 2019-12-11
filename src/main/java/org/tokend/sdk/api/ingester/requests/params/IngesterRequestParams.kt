package org.tokend.sdk.api.ingester.requests.params

import org.tokend.sdk.api.ingester.requests.params.IngesterRequestParams.Companion.Includes
import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see Includes
 */
open class IngesterRequestParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    companion object {
        class Includes {
            companion object {
                const val REQUEST_DETAILS = "request_details"
            }
        }
    }
}