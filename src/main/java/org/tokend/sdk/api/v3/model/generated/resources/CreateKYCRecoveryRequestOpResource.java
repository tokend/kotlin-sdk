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


@Type("operations-create-kyc-recovery-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateKYCRecoveryRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("all_tasks")
    @Nullable
    private Long allTasks;
    
    @Nullable
    public Long getAllTasks() {
        return allTasks;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("signers_data")
    private List<UpdateSignerData> signersData;
    
    public List<? extends UpdateSignerData> getSignersData() {
        return signersData;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            signersData != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
    
    @JsonIgnore
    @Relationship("target_account")
    private AccountResource targetAccount;
    
    public AccountResource getTargetAccount() {
        return targetAccount;
    }
}
