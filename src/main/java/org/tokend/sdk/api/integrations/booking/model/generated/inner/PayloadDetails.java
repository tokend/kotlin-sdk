// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.booking.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PayloadDetails {
    
    @JsonProperty("capacity")
    private Integer capacity;
    
    public Integer getCapacity() {
        return capacity;
    }
    
    @JsonProperty("price")
    private PriceDetails price;
    
    public PriceDetails getPrice() {
        return price;
    }
    
    @JsonProperty("work_days")
    private Map<String, WorkHours> workDays;
    
    public Map<String, ? extends WorkHours> getWorkDays() {
        return workDays;
    }
}
