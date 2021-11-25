// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeAppliedTo {
    
    @JsonProperty("asset")
    private String asset;
    
    public String getAsset() {
        return asset;
    }
    
    @JsonProperty("fee_type")
    private Integer feeType;
    
    public Integer getFeeType() {
        return feeType;
    }
    
    @JsonProperty("fee_type_extended")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum feeTypeExtended;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getFeeTypeExtended() {
        return feeTypeExtended;
    }
    
    @JsonProperty("lower_bound")
    private BigDecimal lowerBound;
    
    public BigDecimal getLowerBound() {
        return lowerBound;
    }
    
    @JsonProperty("subtype")
    private Long subtype;
    
    public Long getSubtype() {
        return subtype;
    }
    
    @JsonProperty("upper_bound")
    private BigDecimal upperBound;
    
    public BigDecimal getUpperBound() {
        return upperBound;
    }
}
