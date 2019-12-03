package org.tokend.sdk.api.integrations.booking.params

import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.utils.ApiDateUtil
import java.util.*

class FreeRoomsParams(
        private val startTime: Date,
        private val endTime: Date,
        private val participantsCount: Int,
        private val roomIds: Collection<String>
) : QueryParams {
    override fun map(): Map<String, Any> {
        return mutableMapOf<String, Any>().apply {
            put("start-time", startTime.time / 1000L)
            put("end-time", endTime.time / 1000L)
            put("participants", participantsCount)
            put("payloads", roomIds.joinToString(","))
        }
    }
}