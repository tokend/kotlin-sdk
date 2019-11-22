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


@Type("operations-bind-external-system-account-id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BindExternalSystemAccountIdOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("external_system_type")
    private Integer externalSystemType;
    
    public Integer getExternalSystemType() {
        return externalSystemType;
    }
    
    @Override
    public boolean isFilled() {
        return             externalSystemType != null 
            && super.isFilled()
        ;
    }
}
