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


@Type("request-details-asset-create")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAssetRequestResource extends BaseReviewableRequestDetailsResource {
    
    @JsonProperty("asset")
    private String assetCode;
    
    public String getAssetCode() {
        return assetCode;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("initial_preissued_amount")
    private BigDecimal initialPreissuedAmount;
    
    public BigDecimal getInitialPreissuedAmount() {
        return initialPreissuedAmount;
    }
    
    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;
    
    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }
    
    @JsonProperty("policies")
    private Integer policies;
    
    public Integer getPolicies() {
        return policies;
    }
    
    @JsonProperty("pre_issuance_asset_signer")
    private String preIssuanceAssetSigner;
    
    public String getPreIssuanceAssetSigner() {
        return preIssuanceAssetSigner;
    }
    
    @JsonProperty("trailing_digits_count")
    private Long trailingDigitsCount;
    
    public Long getTrailingDigitsCount() {
        return trailingDigitsCount;
    }
    
    @JsonProperty("type")
    private Long type;
    
    public Long getType() {
        return type;
    }
    
    @Override
    public boolean isFilled() {
        return             assetCode != null &&
            creatorDetails != null &&
            initialPreissuedAmount != null &&
            maxIssuanceAmount != null &&
            policies != null &&
            preIssuanceAssetSigner != null &&
            trailingDigitsCount != null &&
            type != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
