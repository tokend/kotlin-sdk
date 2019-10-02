// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("base-manage-signer-role-op")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageSignerRoleOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("is_read_only")
    private Boolean isReadOnly;
    
    public Boolean isReadOnly() {
        return isReadOnly;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            isReadOnly != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("role")
    private AccountRoleResource role;
    
    public AccountRoleResource getRole() {
        return role;
    }
    
    @Relationship("rules")
    private List<AccountRuleResource> rules;
    
    public List<? extends AccountRuleResource> getRules() {
        return rules;
    }
}
