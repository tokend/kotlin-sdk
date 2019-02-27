// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationResource extends BaseResource {
    
    @JsonProperty("applied_at")
    private Date appliedAt;
    
    public Date getAppliedAt() {
        return appliedAt;
    }
    
    @Override
    public boolean isFilled() {
        return             appliedAt != null 
        ;
    }
    
    @Relationship("tx")
    private TransactionResource tx;
    
    public TransactionResource getTx() {
        return tx;
    }
    
    @Relationship("details")
    private OperationDetailsResource details;
    
    public OperationDetailsResource getDetails() {
        return details;
    }
    
    @Relationship("source")
    private AccountResource source;
    
    public AccountResource getSource() {
        return source;
    }
}
