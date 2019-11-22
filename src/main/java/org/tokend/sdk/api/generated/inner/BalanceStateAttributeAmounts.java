// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceStateAttributeAmounts {
    
    @JsonProperty("available")
    private BigDecimal available;
    
    public BigDecimal getAvailable() {
        return available;
    }
    
    @JsonProperty("locked")
    private BigDecimal locked;
    
    public BigDecimal getLocked() {
        return locked;
    }
}
