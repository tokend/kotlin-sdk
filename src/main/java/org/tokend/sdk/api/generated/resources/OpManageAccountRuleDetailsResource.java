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


@Type("operations-manage-account-rule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageAccountRuleDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("resource")
    private JsonNode resource;
    
    public JsonNode getResource() {
        return resource;
    }
    
    @JsonProperty("action")
    private JsonNode action;
    
    public JsonNode getAction() {
        return action;
    }
    
    @JsonProperty("is_forbid")
    private Boolean isForbid;
    
    public Boolean isForbid() {
        return isForbid;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean hasAttributes() {
        return             resource != null &&
            action != null &&
            isForbid != null &&
            details != null 
        ;
    }
    
    @Relationship("rule")
    private AccountRuleResource rule;
    
    public AccountRuleResource getRule() {
        return rule;
    }
}
