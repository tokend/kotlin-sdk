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


@Type("account-rules")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountRuleResource extends BaseResource {
    
    @JsonProperty("action")
    private String action;
    
    public String getAction() {
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
    
    @JsonProperty("resource")
    private JsonNode resource;
    
    public JsonNode getResource() {
        return resource;
    }
    
    @Override
    public boolean hasAttributes() {
        return             action != null &&
            isForbid != null &&
            details != null &&
            resource != null 
        ;
    }
}
