// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("balances-state")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceStateResource extends BaseResource {
    
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
    
    @Override
    public boolean isFilled() {
        return             available != null &&
            locked != null 
        ;
    }
}
