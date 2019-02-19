package org.tokend.sdk.api.v3.history.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

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