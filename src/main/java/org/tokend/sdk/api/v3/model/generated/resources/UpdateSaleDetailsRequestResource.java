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


@Type("request-details-update-sale-details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateSaleDetailsRequestResource extends BaseReviewableRequestDetailsResource {
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("sale")
    private SaleResource sale;
    
    public SaleResource getSale() {
        return sale;
    }
}
