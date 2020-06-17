// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.recpayments.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.resources.*;
import org.tokend.sdk.api.integrations.recpayments.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("scheduled-payment-record")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledPaymentRecordResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("last_payment")
    @Nullable
    private Date lastPayment;
    
    @Nullable
    public Date getLastPayment() {
        return lastPayment;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null 
        ;
    }
    
    @Relationship("description")
    private ScheduledPaymentRecordDescriptionResource description;
    
    public ScheduledPaymentRecordDescriptionResource getDescription() {
        return description;
    }
    
    @Relationship("destination_account")
    private BaseResource destinationAccount;
    
    public BaseResource getDestinationAccount() {
        return destinationAccount;
    }
    
    @Relationship("destination_balance")
    private BaseResource destinationBalance;
    
    public BaseResource getDestinationBalance() {
        return destinationBalance;
    }
    
    @Relationship("destination_card")
    private BaseResource destinationCard;
    
    public BaseResource getDestinationCard() {
        return destinationCard;
    }
    
    @Relationship("source_account")
    private BaseResource sourceAccount;
    
    public BaseResource getSourceAccount() {
        return sourceAccount;
    }
    
    @Relationship("source_balance")
    private BaseResource sourceBalance;
    
    public BaseResource getSourceBalance() {
        return sourceBalance;
    }
    
    @Relationship("source_card")
    private BaseResource sourceCard;
    
    public BaseResource getSourceCard() {
        return sourceCard;
    }
}
