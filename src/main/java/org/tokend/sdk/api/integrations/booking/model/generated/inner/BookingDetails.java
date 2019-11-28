// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingDetails {
    
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
    
    @JsonProperty("confirmation_types")
    private List<Integer> confirmationTypes;
    
    public List<? extends Integer> getConfirmationTypes() {
        return confirmationTypes;
    }
    
    @JsonProperty("max_duration")
    private String maxDuration;
    
    public String getMaxDuration() {
        return maxDuration;
    }
    
    @JsonProperty("min_duration")
    private String minDuration;
    
    public String getMinDuration() {
        return minDuration;
    }
    
    @JsonProperty("refund")
    private BigDecimal refund;
    
    public BigDecimal getRefund() {
        return refund;
    }
    
    @JsonProperty("specific_details")
    private Map<String, PayloadDetails> specificDetails;
    
    public Map<String, ? extends PayloadDetails> getSpecificDetails() {
        return specificDetails;
    }
}
