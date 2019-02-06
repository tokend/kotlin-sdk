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
public class ExternalSystemPoolCreation {
    
    @JsonProperty("pool_id")
    private Long poolId;
    
    public Long getPoolId() {
        return poolId;
    }
    
    @JsonProperty("data")
    private String data;
    
    public String getData() {
        return data;
    }
    
    @JsonProperty("parent")
    private Long parent;
    
    public Long getParent() {
        return parent;
    }
    
    @JsonProperty("external_system_type")
    private Integer externalSystemType;
    
    public Integer getExternalSystemType() {
        return externalSystemType;
    }
}
