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


@Type("operations-manage-sale")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageSaleOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private Enum action;
    
    public Enum getAction() {
        return action;
    }
    
    @JsonProperty("sale_id")
    private Long saleId;
    
    public Long getSaleId() {
        return saleId;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            saleId != null 
            && super.isFilled()
        ;
    }
}
