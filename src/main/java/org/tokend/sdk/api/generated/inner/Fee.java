// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fee {
    
    @JsonProperty("calculated_percent")
    private BigDecimal calculatedPercent;
    
    public BigDecimal getCalculatedPercent() {
        return calculatedPercent;
    }
    
    @JsonProperty("fixed")
    private BigDecimal fixed;
    
    public BigDecimal getFixed() {
        return fixed;
    }
}
