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


@Type("request-details-change-role")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangeRoleRequestResource extends BaseReviewableRequestDetailsResource {
    
    @JsonProperty("account_role_to_set")
    private Long accountRoleToSet;
    
    public Long getAccountRoleToSet() {
        return accountRoleToSet;
    }
    
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
    
    @Override
    public boolean isFilled() {
        return             accountRoleToSet != null &&
            creatorDetails != null &&
            sequenceNumber != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("account_to_update_role")
    private AccountResource accountToUpdateRole;
    
    public AccountResource getAccountToUpdateRole() {
        return accountToUpdateRole;
    }
}
