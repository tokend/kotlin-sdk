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


@Type("operations-kyc-recovery")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KYCRecoveryOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("signers_data")
    private List<SignerData> signersData;
    
    public List<? extends SignerData> getSignersData() {
        return signersData;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
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
