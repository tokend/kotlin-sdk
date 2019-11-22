// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-open-swap")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenSwapOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
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
    
    @JsonProperty("source_pay_for_destination")
    private Boolean sourcePayForDestination;
    
    public Boolean sourcePayForDestination() {
        return sourcePayForDestination;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            destinationFee != null &&
            details != null &&
            lockTime != null &&
            secretHash != null &&
            sourceFee != null &&
            sourcePayForDestination != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("destination")
    private AccountResource destination;
    
    public AccountResource getDestination() {
        return destination;
    }
    
    @Relationship("destination_balance")
    private BalanceResource destinationBalance;
    
    public BalanceResource getDestinationBalance() {
        return destinationBalance;
    }
    
    @Relationship("source")
    private AccountResource source;
    
    public AccountResource getSource() {
        return source;
    }
    
    @Relationship("source_balance")
    private BalanceResource sourceBalance;
    
    public BalanceResource getSourceBalance() {
        return sourceBalance;
    }
}
