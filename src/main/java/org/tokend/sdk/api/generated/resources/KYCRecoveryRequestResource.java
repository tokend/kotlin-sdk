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


@Type("request-details-kyc-recovery")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KYCRecoveryRequestResource extends BaseReviewableRequestDetailsResource {
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("sequence_number")
    private Long sequenceNumber;
    
    public Long getSequenceNumber() {
        return sequenceNumber;
    }
    
    @JsonProperty("signers_data")
    private List<UpdateSignerData> signersData;
    
    public List<? extends UpdateSignerData> getSignersData() {
        return signersData;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            sequenceNumber != null &&
            signersData != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("target_account")
    private AccountResource targetAccount;
    
    public AccountResource getTargetAccount() {
        return targetAccount;
    }
}
