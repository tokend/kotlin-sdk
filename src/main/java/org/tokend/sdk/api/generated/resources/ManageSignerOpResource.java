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


@Type("base-manage-signer-op")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageSignerOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("identity")
    private Long identity;
    
    public Long getIdentity() {
        return identity;
    }
    
    @JsonProperty("weight")
    private Long weight;
    
    public Long getWeight() {
        return weight;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            identity != null &&
            weight != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("role")
    private SignerRoleResource role;
    
    public SignerRoleResource getRole() {
        return role;
    }
    
    @Relationship("signer")
    private SignerResource signer;
    
    public SignerResource getSigner() {
        return signer;
    }
}
