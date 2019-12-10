// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-destruction")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DestructionOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            creatorDetails != null &&
            fee != null &&
            securityType != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("balance")
    private BalanceResource balance;
    
    public BalanceResource getBalance() {
        return balance;
    }
}
