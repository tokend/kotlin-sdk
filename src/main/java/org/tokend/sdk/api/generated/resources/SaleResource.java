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


@Type("sales")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleResource extends BaseResource {
    
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
    
    @JsonProperty("sale_state")
    private XdrEnumValue saleState;
    
    public XdrEnumValue getSaleState() {
        return saleState;
    }
    
    @JsonProperty("investors_count")
    private Integer investorsCount;
    
    public Integer getInvestorsCount() {
        return investorsCount;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean hasAttributes() {
        return             startTime != null &&
            endTime != null &&
            saleType != null &&
            saleState != null &&
            investorsCount != null &&
            details != null 
        ;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("quote_assets")
    private List<SaleQuoteAssetResource> quoteAssets;
    
    public List<? extends SaleQuoteAssetResource> getQuoteAssets() {
        return quoteAssets;
    }
    
    @Relationship("default_quote_asset")
    private SaleQuoteAssetResource defaultQuoteAsset;
    
    public SaleQuoteAssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }
}
