// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.scheduler.generated.resources;

import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.booking.model.scheduler.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("events-recurrence")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventRecurrenceResource extends BaseResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("duration")
    @Nullable
    private String duration;
    
    @Nullable
    public String getDuration() {
        return duration;
    }
    
    @JsonProperty("max_participants")
    private Integer maxParticipants;
    
    public Integer getMaxParticipants() {
        return maxParticipants;
    }
    
    @JsonProperty("payload")
    private String payload;
    
    public String getPayload() {
        return payload;
    }
    
    @JsonProperty("recurrence")
    private Recurrence recurrence;
    
    public Recurrence getRecurrence() {
        return recurrence;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            maxParticipants != null &&
            payload != null &&
            recurrence != null 
        ;
    }
    
    @Relationship("calendar")
    private CalendarResource calendar;
    
    public CalendarResource getCalendar() {
        return calendar;
    }
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
}
