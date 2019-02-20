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


@Type("request-details-change-role")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeRoleRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("account_role_to_set")
    private Long accountRoleToSet;
    
    public Long getAccountRoleToSet() {
        return accountRoleToSet;
    }
    
    @JsonProperty("kyc_data")
    private JsonNode kycData;
    
    public JsonNode getKycData() {
        return kycData;
    }
    
    @JsonProperty("sequence_number")
    private Long sequenceNumber;
    
    public Long getSequenceNumber() {
        return sequenceNumber;
    }
    
    @JsonProperty("creator_details")
    private List<JsonNode> creatorDetails;
    
    public List<? extends JsonNode> getCreatorDetails() {
        return creatorDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             accountRoleToSet != null &&
            kycData != null &&
            sequenceNumber != null &&
            creatorDetails != null 
        ;
    }
}
