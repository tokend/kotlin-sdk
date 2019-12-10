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


@Type("operations-update-asset")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateAssetOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("details")
    @Nullable
    private JsonNode details;
    
    @Nullable
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("max_issuance_amount")
    @Nullable
    private BigDecimal maxIssuanceAmount;
    
    @Nullable
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @JsonProperty("state")
    @Nullable
    private Long state;
    
    @Nullable
    public Long getState() {
        return state;
    }
    
    @Override
    public boolean isFilled() {
        return             details != null &&
            maxIssuanceAmount != null &&
            state != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
