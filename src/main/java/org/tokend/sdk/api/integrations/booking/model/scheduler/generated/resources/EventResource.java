// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.scheduler.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.booking.model.scheduler.generated.resources.*;
import org.tokend.sdk.api.integrations.booking.model.scheduler.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("events")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource extends BaseResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("end_time")
    @Nullable
    private Date endTime;
    
    @Nullable
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("lock_time")
    @Nullable
    private Date lockTime;
    
    @Nullable
    public Date getLockTime() {
        return lockTime;
    }
    
    @JsonProperty("max_participants")
    private Integer maxParticipants;
    
    public Integer getMaxParticipants() {
        return maxParticipants;
    }
    
    @JsonProperty("participants")
    private Integer participants;
    
    public Integer getParticipants() {
        return participants;
    }
    
    @JsonProperty("payload")
    private String payload;
    
    public String getPayload() {
        return payload;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @JsonProperty("state")
    private Integer state;
    
    public Integer getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            maxParticipants != null &&
            participants != null &&
            payload != null &&
            startTime != null &&
            state != null 
        ;
    }
    
    @Relationship("attendee")
    private BaseResource attendee;
    
    public BaseResource getAttendee() {
        return attendee;
    }
    
    @Relationship("calendar")
    private CalendarResource calendar;
    
    public CalendarResource getCalendar() {
        return calendar;
    }
    
    @Relationship("holder")
    private BaseResource holder;
    
    public BaseResource getHolder() {
        return holder;
    }
    
    @Relationship("recurrence")
    private EventRecurrenceResource recurrence;
    
    public EventRecurrenceResource getRecurrence() {
        return recurrence;
    }
}
