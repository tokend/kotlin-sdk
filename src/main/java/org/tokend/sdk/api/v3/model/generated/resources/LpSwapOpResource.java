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


@Type("operations-lp-swap")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LpSwapOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("in_amount")
    private BigDecimal inAmount;
    
    public BigDecimal getInAmount() {
        return inAmount;
    }
    
    @JsonProperty("liquidity_pool_id")
    private Integer liquidityPoolId;
    
    public Integer getLiquidityPoolId() {
        return liquidityPoolId;
    }
    
    @JsonProperty("out_amount")
    private BigDecimal outAmount;
    
    public BigDecimal getOutAmount() {
        return outAmount;
    }
    
    @JsonProperty("swap_type")
    private String swapType;
    
    public String getSwapType() {
        return swapType;
    }
    
    @Override
    public boolean isFilled() {
        return             inAmount != null &&
            liquidityPoolId != null &&
            outAmount != null &&
            swapType != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("source_in_balance")
    private BalanceResource sourceInBalance;
    
    public BalanceResource getSourceInBalance() {
        return sourceInBalance;
    }
    
    @JsonIgnore
    @Relationship("source_out_balance")
    private BalanceResource sourceOutBalance;
    
    public BalanceResource getSourceOutBalance() {
        return sourceOutBalance;
    }
}
