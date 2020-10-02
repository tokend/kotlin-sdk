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


@Type("booking-businesses")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BusinessResource extends BaseResource {
    
    @JsonProperty("booking_details")
    private BookingDetails bookingDetails;
    
    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("name")
    private String name;
    
    public String getName() {
        return name;
    }
    
    @JsonProperty("work_days")
    private Map<String, WorkHours> workDays;
    
    public Map<String, ? extends WorkHours> getWorkDays() {
        return workDays;
    }
    
    @Override
    public boolean isFilled() {
        return             bookingDetails != null &&
            details != null &&
            name != null &&
            workDays != null 
        ;
    }
    
    @Relationship("calendar")
    private BaseResource calendar;
    
    public BaseResource getCalendar() {
        return calendar;
    }
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
}
