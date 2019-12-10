// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignerData {
    
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
    
    @JsonProperty("public_key")
    private String publicKey;
    
    public String getPublicKey() {
        return publicKey;
    }
    
    @JsonProperty("role_ids")
    private List<Integer> roleIds;
    
    public List<? extends Integer> getRoleIds() {
        return roleIds;
    }
    
    @JsonProperty("weight")
    private Long weight;
    
    public Long getWeight() {
        return weight;
    }
}
