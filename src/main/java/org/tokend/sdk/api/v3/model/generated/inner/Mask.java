// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mask {
    
    @JsonProperty("flags")
    @Nullable
    private List<org.tokend.sdk.api.v3.model.generated.inner.Enum> flags;
    
    @Nullable
    public List<? extends org.tokend.sdk.api.v3.model.generated.inner.Enum> getFlags() {
        return flags;
    }
    
    @JsonProperty("value")
    @Nullable
    private Integer value;
    
    @Nullable
    public Integer getValue() {
        return value;
    }
}
