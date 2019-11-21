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


@Type("operations-manage-external-system-account-id-pool-entry")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageExternalSystemAccountIDPoolEntryOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.generated.inner.Enum action;
    
    public org.tokend.sdk.api.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("create")
    @Nullable
    private CreateExternalSystemPoolOp create;
    
    @Nullable
    public CreateExternalSystemPoolOp getCreate() {
        return create;
    }
    
    @JsonProperty("remove")
    @Nullable
    private RemoveExternalSystemPoolOp remove;
    
    @Nullable
    public RemoveExternalSystemPoolOp getRemove() {
        return remove;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null 
            && super.isFilled()
        ;
    }
}
