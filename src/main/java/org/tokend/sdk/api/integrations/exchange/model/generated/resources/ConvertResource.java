// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.exchange.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.exchange.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("conversion-requests")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConvertResource extends BaseResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("base")
    private String base;
    
    public String getBase() {
        return base;
    }
    
    @JsonProperty("destination_account")
    private String destinationAccount;
    
    public String getDestinationAccount() {
        return destinationAccount;
    }
    
    @JsonProperty("destination_balance")
    private String destinationBalance;
    
    public String getDestinationBalance() {
        return destinationBalance;
    }
    
    @JsonProperty("quote")
    private String quote;
    
    public String getQuote() {
        return quote;
    }
    
    @JsonProperty("source_account")
    private String sourceAccount;
    
    public String getSourceAccount() {
        return sourceAccount;
    }
    
    @JsonProperty("source_balance")
    private String sourceBalance;
    
    public String getSourceBalance() {
        return sourceBalance;
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
            base != null &&
            destinationAccount != null &&
            destinationBalance != null &&
            quote != null &&
            sourceAccount != null &&
            sourceBalance != null 
        ;
    }
}
