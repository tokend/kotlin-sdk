// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("key-value-entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyValueEntryResource extends BaseResource {
    
    @JsonProperty("value")
    private KeyValueEntryValue value;
    
    public KeyValueEntryValue getValue() {
        return value;
    }
    
    @Override
    public boolean isFilled() {
        return             value != null 
        ;
    }
}
