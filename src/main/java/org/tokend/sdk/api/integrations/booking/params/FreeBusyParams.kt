package org.tokend.sdk.api.integrations.booking.params

import org.tokend.sdk.api.base.params.QueryParams
import org.tokend.sdk.utils.ApiDateUtil
import java.util.*

class FreeBusyParams(
        val payload: String,
        val startTime: Date,
        val endTime: Date
) : QueryParams {
    override fun map(): Map<String, Any> = mutableMapOf<String, Any>().apply {
        put("start-time", startTime.time / 1000L)
        put("end-time", endTime.time / 1000L)
        put("payload", payload)
    }
}