// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.scheduler.generated.resources;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.tokend.sdk.api.base.model.*;


@Type("calendars")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarResource extends BaseResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null 
        ;
    }
    
    @Relationship("events")
    private List<EventResource> events;
    
    public List<? extends EventResource> getEvents() {
        return events;
    }
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
    
    @Relationship("recurrences")
    private List<EventRecurrenceResource> recurrences;
    
    public List<? extends EventRecurrenceResource> getRecurrences() {
        return recurrences;
    }
}
