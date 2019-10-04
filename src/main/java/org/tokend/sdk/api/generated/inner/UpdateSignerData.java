// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSignerData {
    
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
    
    @JsonProperty("role_id")
    private Long roleId;
    
    public Long getRoleId() {
        return roleId;
    }
    
    @JsonProperty("weight")
    private Long weight;
    
    public Long getWeight() {
        return weight;
    }
}
