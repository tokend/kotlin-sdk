// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeAppliedTo {
    
    @JsonProperty("asset")
    private String asset;
    
    public String getAsset() {
        return asset;
    }
    
    @JsonProperty("subtype")
    private Long subtype;
    
    public Long getSubtype() {
        return subtype;
    }
    
    @JsonProperty("fee_type")
    private Integer feeType;
    
    public Integer getFeeType() {
        return feeType;
    }
    
    @JsonProperty("fee_type_extended")
    private XdrEnumValue feeTypeExtended;
    
    public XdrEnumValue getFeeTypeExtended() {
        return feeTypeExtended;
    }
    
    @JsonProperty("lower_bound")
    private BigDecimal lowerBound;
    
    public BigDecimal getLowerBound() {
        return lowerBound;
    }
    
    @JsonProperty("upper_bound")
    private BigDecimal upperBound;
    
    public BigDecimal getUpperBound() {
        return upperBound;
    }
}
