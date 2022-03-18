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


@Type("liquidity-pools")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LiquidityPoolResource extends BaseResource {
    
    @JsonProperty("account_id")
    private String accountId;
    
    public String getAccountId() {
        return accountId;
    }
    
    @JsonProperty("first_reserve")
    private BigDecimal firstReserve;
    
    public BigDecimal getFirstReserve() {
        return firstReserve;
    }
    
    @JsonProperty("lp_tokens_amount")
    private BigDecimal lpTokensAmount;
    
    public BigDecimal getLpTokensAmount() {
        return lpTokensAmount;
    }
    
    @JsonProperty("second_reserve")
    private BigDecimal secondReserve;
    
    public BigDecimal getSecondReserve() {
        return secondReserve;
    }
    
    @Override
    public boolean isFilled() {
        return             accountId != null &&
            firstReserve != null &&
            lpTokensAmount != null &&
            secondReserve != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("first_asset")
    private AssetResource firstAsset;
    
    public AssetResource getFirstAsset() {
        return firstAsset;
    }
    
    @JsonIgnore
    @Relationship("first_balance")
    private BalanceResource firstBalance;
    
    public BalanceResource getFirstBalance() {
        return firstBalance;
    }
    
    @JsonIgnore
    @Relationship("lp_tokens_asset")
    private AssetResource lpTokensAsset;
    
    public AssetResource getLpTokensAsset() {
        return lpTokensAsset;
    }
    
    @JsonIgnore
    @Relationship("second_asset")
    private AssetResource secondAsset;
    
    public AssetResource getSecondAsset() {
        return secondAsset;
    }
    
    @JsonIgnore
    @Relationship("second_balance")
    private BalanceResource secondBalance;
    
    public BalanceResource getSecondBalance() {
        return secondBalance;
    }
}
