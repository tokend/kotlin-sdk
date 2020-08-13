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


@Type("request-details-create-payment")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePaymentRequestResource extends BaseReviewableRequestDetailsResource {
    
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
    
    @JsonProperty("destination_fee")
    private Fee destinationFee;
    
    public Fee getDestinationFee() {
        return destinationFee;
    }
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
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
    
    @JsonProperty("subject")
    private String subject;
    
    public String getSubject() {
        return subject;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            creatorDetails != null &&
            destinationFee != null &&
            reference != null &&
            sourceFee != null &&
            sourcePayForDestination != null &&
            subject != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("balance_from")
    private BalanceResource balanceFrom;
    
    public BalanceResource getBalanceFrom() {
        return balanceFrom;
    }
}
