package org.tokend.sdk.api.integrations.booking.params

import org.tokend.sdk.api.v3.base.JsonApiQueryParams
import org.tokend.sdk.utils.ApiDateUtil
import java.util.*

class FreeRoomsParams(
        private val startTime: Date,
        private val endTime: Date,
        private val participantsCount: Int,
        private val roomIds: Collection<String>
) : JsonApiQueryParams(emptyList()) {
    override fun map(): Map<String, Any> {
        return super.map().toMutableMap().apply {
            putFilter("start-time", ApiDateUtil.formatDateForRequest(startTime))
            putFilter("end-time", ApiDateUtil.formatDateForRequest(endTime))
            putFilter("participants", participantsCount)
            putFilter("payloads", roomIds.joinToString(","))
        }
    }
}