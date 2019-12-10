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


@Type("operations-put-key-value")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PutKeyValueOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("key")
    private String key;
    
    public String getKey() {
        return key;
    }
    
    @JsonProperty("value")
    private KeyValueEntryValue value;
    
    public KeyValueEntryValue getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             key != null &&
            value != null 
            && super.isFilled()
        ;
    }
}
