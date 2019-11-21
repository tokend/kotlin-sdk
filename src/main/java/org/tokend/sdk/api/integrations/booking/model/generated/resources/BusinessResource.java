// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.resources;

import java.math.*;

import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("businesses")
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
    
    @JsonProperty("cancel_till")
    @Nullable
    private String cancelTill;
    
    @Nullable
    public String getCancelTill() {
        return cancelTill;
    }
    
    @JsonProperty("refund")
    private BigDecimal refund;
    
    public BigDecimal getRefund() {
        return refund;
    }
    
    @Override
    public boolean isFilled() {
        return             refund != null 
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
