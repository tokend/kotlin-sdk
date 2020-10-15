// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.booking.model.generated.resources.*;
import org.tokend.sdk.api.integrations.booking.model.generated.inner.*;
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
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            endTime != null &&
            startTime != null 
        ;
    }
    
    @Relationship("attendee")
    private BaseResource attendee;
    
    public BaseResource getAttendee() {
        return attendee;
    }
    
    @Relationship("calendar")
    private BaseResource calendar;
    
    public BaseResource getCalendar() {
        return calendar;
    }
    
    @Relationship("holder")
    private BaseResource holder;
    
    public BaseResource getHolder() {
        return holder;
    }
}
