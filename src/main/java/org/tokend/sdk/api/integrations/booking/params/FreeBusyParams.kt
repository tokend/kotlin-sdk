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
        put("start-time", ApiDateUtil.formatDateForRequest(startTime))
        put("end-time", ApiDateUtil.formatDateForRequest(startTime))
        put("payload", payload)
    }
}