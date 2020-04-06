// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.recpayments.model.generated.resources;

import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("scheduled-payment-record")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduledPaymentRecordResource extends BaseResource {
    
    @JsonProperty("amount")
    private String amount;
    
    public String getAmount() {
        return amount;
    }
    
    @JsonProperty("next_payment")
    @Nullable
    private Date nextPayment;
    
    @Nullable
    public Date getNextPayment() {
        return nextPayment;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null 
        ;
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
}
