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


@Type("operations-manage-limits")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageLimitsOpResource extends BaseResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("create")
    @Nullable
    private ManageLimitsCreationOp create;
    
    @Nullable
    public ManageLimitsCreationOp getCreate() {
        return create;
    }
    
    @JsonProperty("remove")
    @Nullable
    private ManageLimitsRemovalOp remove;
    
    @Nullable
    public ManageLimitsRemovalOp getRemove() {
        return remove;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null 
        ;
    }
}
