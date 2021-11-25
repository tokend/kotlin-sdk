// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.inner;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalSystemData {
    
    @JsonProperty("data")
    private ExternalSystemDataEntry data;
    
    public ExternalSystemDataEntry getData() {
        return data;
    }
    
    @JsonProperty("type")
    private String type;
    
    public String getType() {
        return type;
    }
}
