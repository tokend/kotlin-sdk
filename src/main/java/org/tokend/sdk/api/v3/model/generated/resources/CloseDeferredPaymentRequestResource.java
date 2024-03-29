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


@Type("request-details-close-deferred-payment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloseDeferredPaymentRequestResource extends BaseReviewableRequestDetailsResource {
    
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
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            creatorDetails != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("deferred_payment")
    private DeferredPaymentResource deferredPayment;
    
    public DeferredPaymentResource getDeferredPayment() {
        return deferredPayment;
    }
    
    @JsonIgnore
    @Relationship("destination_account")
    private AccountResource destinationAccount;
    
    public AccountResource getDestinationAccount() {
        return destinationAccount;
    }
    
    @JsonIgnore
    @Relationship("destination_balance")
    private BalanceResource destinationBalance;
    
    public BalanceResource getDestinationBalance() {
        return destinationBalance;
    }
}
