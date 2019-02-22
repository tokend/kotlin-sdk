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
public class XdrEnumBitmask {
    
    @JsonProperty("value")
    private Long value;
    
    public Long getValue() {
        return value;
    }
    
    @JsonProperty("flags")
    private List<XdrEnumValue> flags;
    
    public List<? extends XdrEnumValue> getFlags() {
        return flags;
    }
}
