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


@Type("operations-create-close-deferred-payment-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateCloseDeferredPaymentRequestOpResource extends BaseOperationDetailsResource {
    
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
    
    @Relationship("deferred_payment")
    private DeferredPaymentResource deferredPayment;
    
    public DeferredPaymentResource getDeferredPayment() {
        return deferredPayment;
    }
    
    @Relationship("destination_account")
    private AccountResource destinationAccount;
    
    public AccountResource getDestinationAccount() {
        return destinationAccount;
    }
    
    @Relationship("destination_balance")
    private BalanceResource destinationBalance;
    
    public BalanceResource getDestinationBalance() {
        return destinationBalance;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}
