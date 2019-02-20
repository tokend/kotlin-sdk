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


@Type("operations-manage-signer-rule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageSignerRuleDetailsResource extends OperationDetailsResource {
    
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
    
    @JsonProperty("is_default")
    private Boolean isDefault;
    
    public Boolean isDefault() {
        return isDefault;
    }
    
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
    public boolean hasAttributes() {
        return             resource != null &&
            action != null &&
            isForbid != null &&
            isDefault != null &&
            isReadOnly != null &&
            details != null 
        ;
    }
    
    @Relationship("rule")
    private SignerRuleResource rule;
    
    public SignerRuleResource getRule() {
        return rule;
    }
}
