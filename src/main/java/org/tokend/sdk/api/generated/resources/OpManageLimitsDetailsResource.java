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


@Type("operations-manage-limits")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageLimitsDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("action")
    private XdrEnumValue action;
    
    public XdrEnumValue getAction() {
        return action;
    }
    
    @JsonProperty("create")
    @Nullable
    private LimitsCreation create;
    
    @Nullable
    public LimitsCreation getCreate() {
        return create;
    }
    
    @JsonProperty("remove")
    @Nullable
    private LimitsRemoval remove;
    
    @Nullable
    public LimitsRemoval getRemove() {
        return remove;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null 
            && super.isFilled()
        ;
    }
}
