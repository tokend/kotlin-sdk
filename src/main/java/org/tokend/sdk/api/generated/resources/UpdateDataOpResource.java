// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-update-data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateDataOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("value")
    private JsonNode value;
    
    public JsonNode getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             value != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("data")
    private DataResource data;
    
    public DataResource getData() {
        return data;
    }
}
