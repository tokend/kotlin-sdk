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


@Type("swaps")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapResource extends BaseResource {
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("lock_time")
    private Date lockTime;
    
    public Date getLockTime() {
        return lockTime;
    }
    
    @JsonProperty("state")
    private Integer state;
    
    public Integer getState() {
        return state;
    }
    
    @JsonProperty("secret_hash")
    private String secretHash;
    
    public String getSecretHash() {
        return secretHash;
    }
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("source_fee")
    private Fee sourceFee;
    
    public Fee getSourceFee() {
        return sourceFee;
    }
    
    @JsonProperty("destination_fee")
    private Fee destinationFee;
    
    public Fee getDestinationFee() {
        return destinationFee;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("secret")
    @Nullable
    private String secret;
    
    @Nullable
    public String getSecret() {
        return secret;
    }
    
    @Override
    public boolean isFilled() {
        return             createdAt != null &&
            lockTime != null &&
            state != null &&
            secretHash != null &&
            amount != null &&
            sourceFee != null &&
            destinationFee != null &&
            details != null 
        ;
    }
    
    @Relationship("source")
    private AccountResource source;
    
    public AccountResource getSource() {
        return source;
    }
    
    @Relationship("destination")
    private AccountResource destination;
    
    public AccountResource getDestination() {
        return destination;
    }
    
    @Relationship("source_balance")
    private BalanceResource sourceBalance;
    
    public BalanceResource getSourceBalance() {
        return sourceBalance;
    }
    
    @Relationship("destination_balance")
    private BalanceResource destinationBalance;
    
    public BalanceResource getDestinationBalance() {
        return destinationBalance;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
