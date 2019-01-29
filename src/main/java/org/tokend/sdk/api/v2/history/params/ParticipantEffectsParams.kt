package org.tokend.sdk.api.v2.history.params

import org.tokend.sdk.api.v2.base.JsonApiQueryParams

/**
 * @see ParticipantEffectsParams.Includes
 */
open class ParticipantEffectsParams(
        include: Collection<String>? = null
) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val OPERATION = "operation"
            const val OPERATION_DETAILS = "operation.details"
            const val EFFECT = "effect"
        }
    }
}