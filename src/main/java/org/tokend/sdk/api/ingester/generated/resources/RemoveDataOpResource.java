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


@Type("operations-remove-data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoveDataOpResource extends BaseOperationDetailsResource {
    @Override
    public boolean isFilled() { return super.isFilled(); }
    
    @Relationship("data")
    private DataResource data;
    
    public DataResource getData() {
        return data;
    }
}
