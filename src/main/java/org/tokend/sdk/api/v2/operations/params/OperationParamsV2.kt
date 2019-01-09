package org.tokend.sdk.api.v2.operations.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see OperationParamsV2.Includes
 */
open class OperationParamsV2(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val OPERATION_DETAILS = "operation_details"
            const val SOURCE = "source"
        }
    }
}