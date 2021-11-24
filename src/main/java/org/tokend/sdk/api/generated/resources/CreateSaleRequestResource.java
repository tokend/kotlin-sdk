// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("request-details-sale")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSaleRequestResource extends BaseReviewableRequestDetailsResource {
    
    @JsonProperty("access_definition_type")
    private String accessDefinitionType;
    
    public String getAccessDefinitionType() {
        return accessDefinitionType;
    }
    
    @JsonProperty("base_asset_for_hard_cap")
    private BigDecimal baseAssetForHardCap;
    
    public BigDecimal getBaseAssetForHardCap() {
        return baseAssetForHardCap;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("end_time")
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("hard_cap")
    private BigDecimal hardCap;
    
    public BigDecimal getHardCap() {
        return hardCap;
    }
    
    @JsonProperty("sale_type")
    private org.tokend.sdk.api.generated.inner.Enum saleType;
    
    public org.tokend.sdk.api.generated.inner.Enum getSaleType() {
        return saleType;
    }
    
    @JsonProperty("soft_cap")
    private BigDecimal softCap;
    
    public BigDecimal getSoftCap() {
        return softCap;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @Override
    public boolean isFilled() {
        return             accessDefinitionType != null &&
            baseAssetForHardCap != null &&
            creatorDetails != null &&
            endTime != null &&
            hardCap != null &&
            saleType != null &&
            softCap != null &&
            startTime != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @JsonIgnore
    @Relationship("default_quote_asset")
    private AssetResource defaultQuoteAsset;
    
    public AssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }
    
    @JsonIgnore
    @Relationship("quote_assets")
    private List<AssetResource> quoteAssets;
    
    public List<? extends AssetResource> getQuoteAssets() {
        return quoteAssets;
    }
}
