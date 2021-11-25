// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-manage-key-value")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageKeyValueOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum action;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getAction() {
        return action;
    }
    
    @JsonProperty("key")
    private String key;
    
    public String getKey() {
        return key;
    }
    
    @JsonProperty("value")
    @Nullable
    private KeyValueEntryValue value;
    
    @Nullable
    public KeyValueEntryValue getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            key != null 
            && super.isFilled()
        ;
    }
}
