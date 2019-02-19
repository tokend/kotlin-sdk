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


@Type("rules")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RuleResource extends BaseResource {
    
    @JsonProperty("resource")
    private String resource;
    
    public String getResource() {
        return resource;
    }
    
    @JsonProperty("action")
    private String action;
    
    public String getAction() {
        return action;
    }
    
    @JsonProperty("is_forbidden")
    private Boolean isForbidden;
    
    public Boolean isForbidden() {
        return isForbidden;
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
            isForbidden != null &&
            details != null 
        ;
    }
}
