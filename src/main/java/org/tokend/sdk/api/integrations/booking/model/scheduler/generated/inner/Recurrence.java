// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.scheduler.generated.inner;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Recurrence {
    
    @JsonProperty("dtstart")
    private String dtstart;
    
    public String getDtstart() {
        return dtstart;
    }
    
    @JsonProperty("ex_dates")
    @Nullable
    private List<String> exDates;
    
    @Nullable
    public List<? extends String> getExDates() {
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
    private List<String> rDates;
    
    @Nullable
    public List<? extends String> getRDates() {
        return rDates;
    }
    
    @JsonProperty("r_rules")
    private List<RecurrenceRule> rRules;
    
    public List<? extends RecurrenceRule> getRRules() {
        return rRules;
    }
}
