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


@Type("base-manage-account-role-op")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageAccountRoleOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("role")
    private AccountRoleResource role;
    
    public AccountRoleResource getRole() {
        return role;
    }
    
    @JsonIgnore
    @Relationship("rules")
    private List<AccountRuleResource> rules;
    
    public List<? extends AccountRuleResource> getRules() {
        return rules;
    }
}
