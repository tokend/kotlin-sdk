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
public class KeyValueEntryValue {
    
    @JsonProperty("str")
    @Nullable
    private Str str;
    
    @Nullable
    public Str getStr() {
        return str;
    }
    
    @JsonProperty("type")
    private Enum type;
    
    public Enum getType() {
        return type;
    }
    
    @JsonProperty("u32")
    @Nullable
    private U32 u32;
    
    @Nullable
    public U32 getU32() {
        return u32;
    }
    
    @JsonProperty("u64")
    @Nullable
    private U64 u64;
    
    @Nullable
    public U64 getU64() {
        return u64;
    }
}
