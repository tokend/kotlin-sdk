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


@Type("schedule-payments-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SchedulePaymentResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("destination_type")
    private org.tokend.sdk.api.integrations.recpayments.model.generated.inner.Enum destinationType;
    
    public org.tokend.sdk.api.integrations.recpayments.model.generated.inner.Enum getDestinationType() {
        return destinationType;
    }
    
    @JsonProperty("r_rule")
    private String rRule;
    
    public String getRRule() {
        return rRule;
    }
    
    @JsonProperty("send_immediately")
    private Boolean sendImmediately;
    
    public Boolean sendImmediately() {
        return sendImmediately;
    }
    
    @JsonProperty("subject")
    @Nullable
    private String subject;
    
    @Nullable
    public String getSubject() {
        return subject;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            destinationType != null &&
            rRule != null &&
            sendImmediately != null 
        ;
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
