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


@Type("deferred-payments")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeferredPaymentResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("state")
    private String state;
    
    public String getState() {
        return state;
    }
    
    @JsonProperty("state_i")
    private Integer stateI;
    
    public Integer getStateI() {
        return stateI;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            details != null &&
            state != null &&
            stateI != null 
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
