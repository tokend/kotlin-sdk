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


@Type("signer-rules")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerRuleResource extends BaseResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
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
            resource != null 
        ;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
}
