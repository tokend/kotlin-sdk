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


@Type("request-details-update-sale-details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSaleDetailsRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("new_details")
    private JsonNode newDetails;
    
    public JsonNode getNewDetails() {
        return newDetails;
    }
    
    @Override
    public boolean hasAttributes() {
        return             newDetails != null 
        ;
    }
    
    @Relationship("sale")
    private SaleResource sale;
    
    public SaleResource getSale() {
        return sale;
    }
}
