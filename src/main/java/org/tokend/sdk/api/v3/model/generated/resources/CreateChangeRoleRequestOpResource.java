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


@Type("operations-create-change-role-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateChangeRoleRequestOpResource extends BaseOperationDetailsResource {
    
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
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("account_to_update_role")
    private AccountResource accountToUpdateRole;
    
    public AccountResource getAccountToUpdateRole() {
        return accountToUpdateRole;
    }
    
    @JsonIgnore
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
    
    @JsonIgnore
    @Relationship("role_to_set")
    private AccountRoleResource roleToSet;
    
    public AccountRoleResource getRoleToSet() {
        return roleToSet;
    }
}
