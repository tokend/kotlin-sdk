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


@Type("operations-payment-v2")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpPaymentDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("source_fee")
    private Fee sourceFee;
    
    public Fee getSourceFee() {
        return sourceFee;
    }
    
    @JsonProperty("destination_fee")
    private Fee destinationFee;
    
    public Fee getDestinationFee() {
        return destinationFee;
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
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @Override
    public boolean hasAttributes() {
        return             amount != null &&
            sourceFee != null &&
            destinationFee != null &&
            sourcePayForDestination != null &&
            subject != null &&
            reference != null 
        ;
    }
    
    @Relationship("account_from")
    private AccountResource accountFrom;
    
    public AccountResource getAccountFrom() {
        return accountFrom;
    }
    
    @Relationship("account_to")
    private AccountResource accountTo;
    
    public AccountResource getAccountTo() {
        return accountTo;
    }
    
    @Relationship("balance_from")
    private BalanceResource balanceFrom;
    
    public BalanceResource getBalanceFrom() {
        return balanceFrom;
    }
    
    @Relationship("balance_to")
    private BalanceResource balanceTo;
    
    public BalanceResource getBalanceTo() {
        return balanceTo;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
