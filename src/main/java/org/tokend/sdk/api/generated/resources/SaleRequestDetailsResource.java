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


@Type("request-details-sale")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleRequestDetailsResource extends RequestDetailsResource {
    
    @JsonProperty("base_asset_for_hard_cap")
    private String baseAssetForHardCap;
    
    public String getBaseAssetForHardCap() {
        return baseAssetForHardCap;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @JsonProperty("end_time")
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("sale_type")
    private XdrEnumValue saleType;
    
    public XdrEnumValue getSaleType() {
        return saleType;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean hasAttributes() {
        return             baseAssetForHardCap != null &&
            startTime != null &&
            endTime != null &&
            saleType != null &&
            details != null 
        ;
    }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("quote_assets")
    private List<QuoteAssetResource> quoteAssets;
    
    public List<? extends QuoteAssetResource> getQuoteAssets() {
        return quoteAssets;
    }
    
    @Relationship("default_quote_asset")
    private QuoteAssetResource defaultQuoteAsset;
    
    public QuoteAssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }
}
