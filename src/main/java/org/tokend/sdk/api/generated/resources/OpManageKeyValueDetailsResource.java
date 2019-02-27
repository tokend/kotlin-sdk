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


@Type("operations-manage-key-value")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageKeyValueDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("key")
    private String key;
    
    public String getKey() {
        return key;
    }
    
    @JsonProperty("action")
    private XdrEnumValue action;
    
    public XdrEnumValue getAction() {
        return action;
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
        return             key != null &&
            action != null 
            && super.isFilled()
        ;
    }
}
