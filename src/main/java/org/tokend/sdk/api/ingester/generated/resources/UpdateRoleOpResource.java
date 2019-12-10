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


@Type("operations-update-role")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRoleOpResource extends BaseOperationDetailsResource {
    
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
    
    @Relationship("role")
    private RoleResource role;
    
    public RoleResource getRole() {
        return role;
    }
    
    @Relationship("rules")
    private List<RuleResource> rules;
    
    public List<? extends RuleResource> getRules() {
        return rules;
    }
}
