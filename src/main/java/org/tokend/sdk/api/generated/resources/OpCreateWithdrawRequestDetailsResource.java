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


@Type("operations-create-withdrawal-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCreateWithdrawRequestDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             amount != null &&
            fee != null &&
            externalDetails != null 
        ;
    }
    
    @Relationship("balance")
    private BalanceResource balance;
    
    public BalanceResource getBalance() {
        return balance;
    }
}
