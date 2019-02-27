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


@Type("signers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerResource extends BaseResource {
    
    @JsonProperty("weight")
    private Long weight;
    
    public Long getWeight() {
        return weight;
    }
    
    @JsonProperty("identity")
    private Long identity;
    
    public Long getIdentity() {
        return identity;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean isFilled() {
        return             weight != null &&
            identity != null &&
            details != null 
        ;
    }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
    
    @Relationship("role")
    private SignerRoleResource role;
    
    public SignerRoleResource getRole() {
        return role;
    }
}
