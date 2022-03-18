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


@Type("base-lp-manage-liquidity-op")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LpManageLiquidityOpResource extends BaseResource {
    
    @JsonProperty("first_asset_amount")
    private BigDecimal firstAssetAmount;
    
    public BigDecimal getFirstAssetAmount() {
        return firstAssetAmount;
    }
    
    @JsonProperty("liquidity_pool_id")
    private Integer liquidityPoolId;
    
    public Integer getLiquidityPoolId() {
        return liquidityPoolId;
    }
    
    @JsonProperty("lp_tokens_amount")
    private BigDecimal lpTokensAmount;
    
    public BigDecimal getLpTokensAmount() {
        return lpTokensAmount;
    }
    
    @JsonProperty("second_asset_amount")
    private BigDecimal secondAssetAmount;
    
    public BigDecimal getSecondAssetAmount() {
        return secondAssetAmount;
    }
    
    @Override
    public boolean isFilled() {
        return             firstAssetAmount != null &&
            liquidityPoolId != null &&
            lpTokensAmount != null &&
            secondAssetAmount != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("first_balance")
    private BalanceResource firstBalance;
    
    public BalanceResource getFirstBalance() {
        return firstBalance;
    }
    
    @JsonIgnore
    @Relationship("second_balance")
    private BalanceResource secondBalance;
    
    public BalanceResource getSecondBalance() {
        return secondBalance;
    }
}
