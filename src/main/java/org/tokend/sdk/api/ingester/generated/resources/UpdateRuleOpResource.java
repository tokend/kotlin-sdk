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


@Type("operations-update-rule")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateRuleOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private Map<String, JsonNode> action;
    
    public Map<String, ? extends JsonNode> getAction() {
        return action;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("forbids")
    private Boolean forbids;
    
    public Boolean forbids() {
        return forbids;
    }
    
    @JsonProperty("resource")
    private Map<String, JsonNode> resource;
    
    public Map<String, ? extends JsonNode> getResource() {
        return resource;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            details != null &&
            forbids != null &&
            resource != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("rule")
    private RuleResource rule;
    
    public RuleResource getRule() {
        return rule;
    }
}
