package org.tokend.sdk.api.integrations.recpayments.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see Includes
 */
class ScheduledPaymentParams (include: Collection<String>?) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val DESCRIPTION = "description"
        }
    }
}