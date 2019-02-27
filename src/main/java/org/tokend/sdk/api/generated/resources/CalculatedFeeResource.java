// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("calculated-fee")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculatedFeeResource extends BaseResource {
    
    @JsonProperty("fixed")
    private BigDecimal fixed;
    
    public BigDecimal getFixed() {
        return fixed;
    }
    
    @JsonProperty("calculated_percent")
    private BigDecimal calculatedPercent;
    
    public BigDecimal getCalculatedPercent() {
        return calculatedPercent;
    }
    
    @Override
    public boolean isFilled() {
        return             fixed != null &&
            calculatedPercent != null 
        ;
    }
}
