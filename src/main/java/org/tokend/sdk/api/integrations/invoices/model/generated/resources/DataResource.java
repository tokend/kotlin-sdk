// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.invoices.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.invoices.model.generated.resources.*;
import org.tokend.sdk.api.integrations.invoices.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("datas")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataResource extends BaseResource {
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
    }
    
    @JsonProperty("value")
    private JsonNode value;
    
    public JsonNode getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             securityType != null &&
            value != null 
        ;
    }
    
    @Relationship("owner")
    private BaseResource owner;
    
    public BaseResource getOwner() {
        return owner;
    }
}
