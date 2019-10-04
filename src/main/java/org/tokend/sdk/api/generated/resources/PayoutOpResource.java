// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-payout")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayoutOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("actual_fee")
    private Fee actualFee;
    
    public Fee getActualFee() {
        return actualFee;
    }
    
    @JsonProperty("actual_payout_amount")
    private BigDecimal actualPayoutAmount;
    
    public BigDecimal getActualPayoutAmount() {
        return actualPayoutAmount;
    }
    
    @JsonProperty("expected_fee")
    private Fee expectedFee;
    
    public Fee getExpectedFee() {
        return expectedFee;
    }
    
    @JsonProperty("max_payout_amount")
    private BigDecimal maxPayoutAmount;
    
    public BigDecimal getMaxPayoutAmount() {
        return maxPayoutAmount;
    }
    
    @JsonProperty("min_asset_holder_amount")
    private BigDecimal minAssetHolderAmount;
    
    public BigDecimal getMinAssetHolderAmount() {
        return minAssetHolderAmount;
    }
    
    @JsonProperty("min_payout_amount")
    private BigDecimal minPayoutAmount;
    
    public BigDecimal getMinPayoutAmount() {
        return minPayoutAmount;
    }
    
    @Override
    public boolean isFilled() {
        return             actualFee != null &&
            actualPayoutAmount != null &&
            expectedFee != null &&
            maxPayoutAmount != null &&
            minAssetHolderAmount != null &&
            minPayoutAmount != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("source_account")
    private AccountResource sourceAccount;
    
    public AccountResource getSourceAccount() {
        return sourceAccount;
    }
    
    @Relationship("source_balance")
    private BalanceResource sourceBalance;
    
    public BalanceResource getSourceBalance() {
        return sourceBalance;
    }
}
