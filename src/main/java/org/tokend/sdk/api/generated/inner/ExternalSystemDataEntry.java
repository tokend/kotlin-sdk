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
public class ExternalSystemDataEntry {
    
    @JsonProperty("address")
    private String address;
    
    public String getAddress() {
        return address;
    }
    
    @JsonProperty("payload")
    @Nullable
    private String payload;
    
    @Nullable
    public String getPayload() {
        return payload;
    }
}
