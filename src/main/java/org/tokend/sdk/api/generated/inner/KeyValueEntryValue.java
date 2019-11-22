// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyValueEntryValue {
    
    @JsonProperty("str")
    @Nullable
    private String str;
    
    @Nullable
    public String getStr() {
        return str;
    }
    
    @JsonProperty("type")
    private org.tokend.sdk.api.generated.inner.Enum type;
    
    public org.tokend.sdk.api.generated.inner.Enum getType() {
        return type;
    }
    
    @JsonProperty("u32")
    @Nullable
    private Long u32;
    
    @Nullable
    public Long getU32() {
        return u32;
    }
    
    @JsonProperty("u64")
    @Nullable
    private Long u64;
    
    @Nullable
    public Long getU64() {
        return u64;
    }
}
