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
public class Mask {
    
    @JsonProperty("flags")
    private List<Enum> flags;
    
    public List<? extends Enum> getFlags() {
        return flags;
    }
    
    @JsonProperty("value")
    private Integer value;
    
    public Integer getValue() {
        return value;
    }
}
