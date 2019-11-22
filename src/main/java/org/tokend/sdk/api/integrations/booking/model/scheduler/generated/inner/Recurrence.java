// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.scheduler.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recurrence {
    
    @JsonProperty("dtstart")
    private Date dtstart;
    
    public Date getDtstart() {
        return dtstart;
    }
    
    @JsonProperty("ex_dates")
    @Nullable
    private List<Date> exDates;
    
    @Nullable
    public List<? extends Date> getExDates() {
        return exDates;
    }
    
    @JsonProperty("floating_location")
    @Nullable
    private Boolean floatingLocation;
    
    @Nullable
    public Boolean floatingLocation() {
        return floatingLocation;
    }
    
    @JsonProperty("r_dates")
    @Nullable
    private List<Date> rDates;
    
    @Nullable
    public List<? extends Date> getRDates() {
        return rDates;
    }
    
    @JsonProperty("r_rules")
    private List<RecurrenceRule> rRules;
    
    public List<? extends RecurrenceRule> getRRules() {
        return rRules;
    }
}
