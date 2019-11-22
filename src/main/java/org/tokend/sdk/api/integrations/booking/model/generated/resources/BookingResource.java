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


@Type("bookings")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("cancel_till")
    private Date cancelTill;
    
    public Date getCancelTill() {
        return cancelTill;
    }
    
    @JsonProperty("confirmation_type")
    private org.tokend.sdk.api.integrations.booking.model.generated.inner.Enum confirmationType;
    
    public org.tokend.sdk.api.integrations.booking.model.generated.inner.Enum getConfirmationType() {
        return confirmationType;
    }
    
    @JsonProperty("end_time")
    private Date endTime;
    
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
    
    @JsonProperty("participants")
    private Integer participants;
    
    public Integer getParticipants() {
        return participants;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @JsonProperty("state")
    private org.tokend.sdk.api.integrations.booking.model.generated.inner.Enum state;
    
    public org.tokend.sdk.api.integrations.booking.model.generated.inner.Enum getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            cancelTill != null &&
            confirmationType != null &&
            endTime != null &&
            participants != null &&
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
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
}
