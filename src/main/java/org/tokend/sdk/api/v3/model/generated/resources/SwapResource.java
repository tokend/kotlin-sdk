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


@Type("swaps")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwapResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
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
    
    @JsonProperty("lock_time")
    private Date lockTime;
    
    public Date getLockTime() {
        return lockTime;
    }
    
    @JsonProperty("secret")
    @Nullable
    private String secret;
    
    @Nullable
    public String getSecret() {
        return secret;
    }
    
    @JsonProperty("secret_hash")
    private String secretHash;
    
    public String getSecretHash() {
        return secretHash;
    }
    
    @JsonProperty("source_fee")
    private Fee sourceFee;
    
    public Fee getSourceFee() {
        return sourceFee;
    }
    
    @JsonProperty("state")
    private Integer state;
    
    public Integer getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            createdAt != null &&
            destinationFee != null &&
            details != null &&
            lockTime != null &&
            secretHash != null &&
            sourceFee != null &&
            state != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @JsonIgnore
    @Relationship("destination")
    private AccountResource destination;
    
    public AccountResource getDestination() {
        return destination;
    }
    
    @JsonIgnore
    @Relationship("destination_balance")
    private BalanceResource destinationBalance;
    
    public BalanceResource getDestinationBalance() {
        return destinationBalance;
    }
    
    @JsonIgnore
    @Relationship("source")
    private AccountResource source;
    
    public AccountResource getSource() {
        return source;
    }
    
    @JsonIgnore
    @Relationship("source_balance")
    private BalanceResource sourceBalance;
    
    public BalanceResource getSourceBalance() {
        return sourceBalance;
    }
}
