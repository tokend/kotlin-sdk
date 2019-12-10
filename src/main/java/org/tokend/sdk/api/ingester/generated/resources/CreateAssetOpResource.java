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


@Type("operations-create-asset")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAssetOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("code")
    @Nullable
    private String code;
    
    @Nullable
    public String getCode() {
        return code;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;
    
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @JsonProperty("security_type")
    private Long securityType;
    
    public Long getSecurityType() {
        return securityType;
    }
    
    @JsonProperty("trailing_digits_count")
    private Long trailingDigitsCount;
    
    public Long getTrailingDigitsCount() {
        return trailingDigitsCount;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            maxIssuanceAmount != null &&
            securityType != null &&
            trailingDigitsCount != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
