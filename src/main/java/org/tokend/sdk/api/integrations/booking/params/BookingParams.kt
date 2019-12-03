package org.tokend.sdk.api.integrations.booking.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams

/**
 * @see BookingParams.Includes
 */
class BookingParams(include: Collection<String>?) : JsonApiQueryParams(include) {
    class Includes {
        companion object {
            const val EVENT = "event"
        }
    }
}