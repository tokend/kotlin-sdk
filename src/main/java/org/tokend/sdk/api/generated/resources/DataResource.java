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


@Type("datas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResource extends BaseResource {
    
    @JsonProperty("type")
    private Long type;
    
    public Long getType() {
        return type;
    }
    
    @JsonProperty("value")
    private JsonNode value;
    
    public JsonNode getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             type != null &&
            value != null 
        ;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
}
