// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("signer-roles")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerRoleResource extends BaseResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null 
        ;
    }
    
    @JsonIgnore
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @JsonIgnore
    @Relationship("rules")
    private List<SignerRuleResource> rules;
    
    public List<? extends SignerRuleResource> getRules() {
        return rules;
    }
}
