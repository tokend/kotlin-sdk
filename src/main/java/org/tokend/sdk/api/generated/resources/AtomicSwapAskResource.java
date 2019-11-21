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


@Type("atomic-swap-ask")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtomicSwapAskResource extends BaseResource {
    
    @JsonProperty("available_amount")
    private BigDecimal availableAmount;
    
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("details")
    private String details;
    
    public String getDetails() {
        return details;
    }
    
    @JsonProperty("is_canceled")
    private Boolean isCanceled;
    
    public Boolean isCanceled() {
        return isCanceled;
    }
    
    @JsonProperty("locked_amount")
    private BigDecimal lockedAmount;
    
    public BigDecimal getLockedAmount() {
        return lockedAmount;
    }
    
    @Override
    public boolean isFilled() {
        return             availableAmount != null &&
            createdAt != null &&
            details != null &&
            isCanceled != null &&
            lockedAmount != null 
        ;
    }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("base_balance")
    private BalanceResource baseBalance;
    
    public BalanceResource getBaseBalance() {
        return baseBalance;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @Relationship("quote_assets")
    private List<AtomicSwapQuoteAssetResource> quoteAssets;
    
    public List<? extends AtomicSwapQuoteAssetResource> getQuoteAssets() {
        return quoteAssets;
    }
}
