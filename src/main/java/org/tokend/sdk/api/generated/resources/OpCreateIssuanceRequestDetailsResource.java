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


@Type("operations-create-issuance-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCreateIssuanceRequestDetailsResource extends OperationDetailsResource {
    
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
    
    @JsonProperty("reference")
    private String reference;
    
    public String getReference() {
        return reference;
    }
    
    @JsonProperty("all_tasks")
    @Nullable
    private Long allTasks;
    
    @Nullable
    public Long getAllTasks() {
        return allTasks;
    }
    
    @JsonProperty("external_details")
    private JsonNode externalDetails;
    
    public JsonNode getExternalDetails() {
        return externalDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             fee != null &&
            amount != null &&
            reference != null &&
            externalDetails != null 
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
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
