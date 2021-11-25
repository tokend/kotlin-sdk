// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("quote-assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteAssetResource extends BaseResource {
    
    @JsonProperty("current_cap")
    private BigDecimal currentCap;
    
    public BigDecimal getCurrentCap() {
        return currentCap;
    }
    
    @JsonProperty("hard_cap")
    private BigDecimal hardCap;
    
    public BigDecimal getHardCap() {
        return hardCap;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("soft_cap")
    @Nullable
    private BigDecimal softCap;
    
    @Nullable
    public BigDecimal getSoftCap() {
        return softCap;
    }
    
    @JsonProperty("total_current_cap")
    private BigDecimal totalCurrentCap;
    
    public BigDecimal getTotalCurrentCap() {
        return totalCurrentCap;
    }
    
    @Override
    public boolean isFilled() {
        return             currentCap != null &&
            hardCap != null &&
            price != null &&
            totalCurrentCap != null 
        ;
    }
}
