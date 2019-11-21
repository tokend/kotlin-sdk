// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.resources;

import java.math.*;

import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("bookings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("cancel_till")
    private String cancelTill;
    
    public String getCancelTill() {
        return cancelTill;
    }
    
    @JsonProperty("confirmation_type")
    private Integer confirmationType;
    
    public Integer getConfirmationType() {
        return confirmationType;
    }
    
    @JsonProperty("end_time")
    private String endTime;
    
    public String getEndTime() {
        return endTime;
    }
    
    @JsonProperty("lock_time")
    @Nullable
    private String lockTime;
    
    @Nullable
    public String getLockTime() {
        return lockTime;
    }
    
    @JsonProperty("start_time")
    private String startTime;
    
    public String getStartTime() {
        return startTime;
    }
    
    @JsonProperty("state")
    private Integer state;
    
    public Integer getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            cancelTill != null &&
            confirmationType != null &&
            endTime != null &&
            startTime != null &&
            state != null 
        ;
    }
    
    @Relationship("asset")
    private BaseResource asset;
    
    public BaseResource getAsset() {
        return asset;
    }
    
    @Relationship("event")
    private EventResource event;
    
    public EventResource getEvent() {
        return event;
    }
    
    @Relationship("participant")
    private BaseResource participant;
    
    public BaseResource getParticipant() {
        return participant;
    }
}
