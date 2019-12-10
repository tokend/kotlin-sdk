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


@Type("effects-roles-changed")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EffectRolesChangeResource extends BaseEffectResource {
    
    @JsonProperty("added_roles")
    private List<Long> addedRoles;
    
    public List<? extends Long> getAddedRoles() {
        return addedRoles;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("removed_roles")
    private List<Long> removedRoles;
    
    public List<? extends Long> getRemovedRoles() {
        return removedRoles;
    }
    
    @Override
    public boolean isFilled() {
        return             addedRoles != null &&
            details != null &&
            removedRoles != null 
            && super.isFilled()
        ;
    }
}
