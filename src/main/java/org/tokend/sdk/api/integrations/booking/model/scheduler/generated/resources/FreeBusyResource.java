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


@Type("freebusy")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FreeBusyResource extends BaseResource {
    
    @JsonProperty("end_time")
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
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
    
    @Override
    public boolean isFilled() {
        return             endTime != null &&
            participants != null &&
            payload != null &&
            startTime != null 
        ;
    }
    
    @Relationship("events")
    private List<EventResource> events;
    
    public List<? extends EventResource> getEvents() {
        return events;
    }
}
