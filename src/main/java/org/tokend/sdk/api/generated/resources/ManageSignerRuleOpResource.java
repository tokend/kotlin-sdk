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


@Type("base-manage-signer-rule-op")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageSignerRuleOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private Enum action;
    
    public Enum getAction() {
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
    
    @JsonProperty("resource")
    private JsonNode resource;
    
    public JsonNode getResource() {
        return resource;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            details != null &&
            forbids != null &&
            isDefault != null &&
            isReadOnly != null &&
            resource != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("rule")
    private SignerRuleResource rule;
    
    public SignerRuleResource getRule() {
        return rule;
    }
}
