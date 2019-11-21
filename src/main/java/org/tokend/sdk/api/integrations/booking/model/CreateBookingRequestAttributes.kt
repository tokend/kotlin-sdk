package org.tokend.sdk.api.integrations.booking.model

import com.google.gson.annotations.SerializedName
import java.util.*

class CreateBookingRequestAttributes(
        @SerializedName("type")
        val type: Int,
        @SerializedName("source")
        val sourceAccount: String,
        @SerializedName("participants")
        val participantsCount: Int,
        @SerializedName("details")
        val details: Any,
        @SerializedName("start_time")
        val startTime: Date? = null,
        @SerializedName("end_time")
        val endTime: Date? = null,
        @SerializedName("payload")
        val payload: String? = null,
        @SerializedName("event_id")
        val eventId: Int? = null
)