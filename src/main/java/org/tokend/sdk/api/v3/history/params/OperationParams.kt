package org.tokend.sdk.api.v3.history.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see OperationParams.Includes
 */
open class OperationParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {

    class Includes {
        companion object {
            const val OPERATION_DETAILS = "operation.details"
        }
    }
}