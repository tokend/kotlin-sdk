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


@Type("operations-check-sale-state")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCheckSaleStateDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("effect")
    private XdrEnumValue effect;
    
    public XdrEnumValue getEffect() {
        return effect;
    }
    
    @Override
    public boolean isFilled() {
        return             effect != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("sale")
    private SaleResource sale;
    
    public SaleResource getSale() {
        return sale;
    }
}
