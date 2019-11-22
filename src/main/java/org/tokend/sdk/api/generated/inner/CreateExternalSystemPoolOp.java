// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateExternalSystemPoolOp {
    
    @JsonProperty("data")
    private String data;
    
    public String getData() {
        return data;
    }
    
    @JsonProperty("external_system_type")
    private Integer externalSystemType;
    
    public Integer getExternalSystemType() {
        return externalSystemType;
    }
    
    @JsonProperty("parent")
    private Long parent;
    
    public Long getParent() {
        return parent;
    }
    
    @JsonProperty("pool_id")
    private Long poolId;
    
    public Long getPoolId() {
        return poolId;
    }
}
