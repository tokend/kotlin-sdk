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


@Type("base-effect-balance-change")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EffectBalanceChangeResource extends BaseEffectResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("modifier")
    private Long modifier;
    
    public Long getModifier() {
        return modifier;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            fee != null &&
            modifier != null 
            && super.isFilled()
        ;
    }
}
