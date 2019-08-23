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
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("available_amount")
    private BigDecimal availableAmount;
    
    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }
    
    @JsonProperty("locked_amount")
    private BigDecimal lockedAmount;
    
    public BigDecimal getLockedAmount() {
        return lockedAmount;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("is_canceled")
    private Boolean isCanceled;
    
    public Boolean isCanceled() {
        return isCanceled;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            availableAmount != null &&
            lockedAmount != null &&
            createdAt != null &&
            isCanceled != null 
        ;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @Relationship("base_balance")
    private BalanceResource baseBalance;
    
    public BalanceResource getBaseBalance() {
        return baseBalance;
    }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("quote_assets")
    private List<AtomicSwapQuoteAssetResource> quoteAssets;
    
    public List<? extends AtomicSwapQuoteAssetResource> getQuoteAssets() {
        return quoteAssets;
    }
}
