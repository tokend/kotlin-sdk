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
    
    @JsonProperty("available_from")
    @Nullable
    private String availableFrom;
    
    @Nullable
    public String getAvailableFrom() {
        return availableFrom;
    }
    
    @JsonProperty("available_till")
    @Nullable
    private String availableTill;
    
    @Nullable
    public String getAvailableTill() {
        return availableTill;
    }
    
    @JsonProperty("booking_details")
    private BookingDetails bookingDetails;
    
    public BookingDetails getBookingDetails() {
        return bookingDetails;
    }
    
    @JsonProperty("cancel_till")
    @Nullable
    private String cancelTill;
    
    @Nullable
    public String getCancelTill() {
        return cancelTill;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("work_days")
    @Nullable
    private Map<String, WorkHours> workDays;
    
    @Nullable
    public Map<String, ? extends WorkHours> getWorkDays() {
        return workDays;
    }
    
    @Override
    public boolean isFilled() {
        return             bookingDetails != null &&
            details != null 
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
