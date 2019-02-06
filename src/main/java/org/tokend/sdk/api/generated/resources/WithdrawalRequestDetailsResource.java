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


@Type("request-details-withdrawal")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @JsonProperty("reviewer_details")
    private JsonNode reviewerDetails;
    
    public JsonNode getReviewerDetails() {
        return reviewerDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             fee != null &&
            amount != null &&
            externalDetails != null &&
            reviewerDetails != null 
        ;
    }
    
    @Relationship("balance")
    private BalanceResource balance;
    
    public BalanceResource getBalance() {
        return balance;
    }
}
