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


@Type("operations-manage-signer-role")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageSignerRoleDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("is_read_only")
    private Boolean isReadOnly;
    
    public Boolean isReadOnly() {
        return isReadOnly;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean isFilled() {
        return             isReadOnly != null &&
            details != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("role")
    private SignerRoleResource role;
    
    public SignerRoleResource getRole() {
        return role;
    }
    
    @Relationship("rules")
    private List<SignerRuleResource> rules;
    
    public List<? extends SignerRuleResource> getRules() {
        return rules;
    }
}
