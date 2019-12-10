// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-issuance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuanceOpResource extends BaseOperationDetailsResource {
    
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
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
    }
    
    @Override
    public boolean isFilled() {
        return             amount != null &&
            creatorDetails != null &&
            fee != null &&
            reference != null &&
            securityType != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("receiver_account")
    private AccountResource receiverAccount;
    
    public AccountResource getReceiverAccount() {
        return receiverAccount;
    }
    
    @Relationship("receiver_balance")
    private BalanceResource receiverBalance;
    
    public BalanceResource getReceiverBalance() {
        return receiverBalance;
    }
}
