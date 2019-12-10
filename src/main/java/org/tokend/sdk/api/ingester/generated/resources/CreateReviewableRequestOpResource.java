// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-create-reviewable-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateReviewableRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
    }
    
    @Override
    public boolean isFilled() {
        return             securityType != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("operations")
    private List<BaseOperationDetailsResource> operations;
    
    public List<? extends BaseOperationDetailsResource> getOperations() {
        return operations;
    }
}
