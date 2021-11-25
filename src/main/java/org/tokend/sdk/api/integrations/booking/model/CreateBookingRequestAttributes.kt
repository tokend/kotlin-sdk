package org.tokend.sdk.api.integrations.booking.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

class CreateBookingRequestAttributes(
        @JsonProperty("type")
        val type: Int,
        @JsonProperty("source")
        val sourceAccount: String,
        @JsonProperty("participants")
        val participantsCount: Int,
        @JsonProperty("details")
        val details: Any,
        @JsonProperty("start_time")
        val startTime: Date? = null,
        @JsonProperty("end_time")
        val endTime: Date? = null,
        @JsonProperty("payload")
        val payload: String? = null,
        @JsonProperty("event_id")
        val eventId: Int? = null
)