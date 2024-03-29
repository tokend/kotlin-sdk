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


@Type("sales")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleResource extends BaseResource {
    
    @JsonProperty("access_definition_type")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum accessDefinitionType;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getAccessDefinitionType() {
        return accessDefinitionType;
    }
    
    @JsonProperty("base_hard_cap")
    private BigDecimal baseHardCap;
    
    public BigDecimal getBaseHardCap() {
        return baseHardCap;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @JsonProperty("end_time")
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("sale_state")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum saleState;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getSaleState() {
        return saleState;
    }
    
    @JsonProperty("sale_type")
    private org.tokend.sdk.api.v3.model.generated.inner.Enum saleType;
    
    public org.tokend.sdk.api.v3.model.generated.inner.Enum getSaleType() {
        return saleType;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @Override
    public boolean isFilled() {
        return             accessDefinitionType != null &&
            baseHardCap != null &&
            details != null &&
            endTime != null &&
            saleState != null &&
            saleType != null &&
            startTime != null 
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
    private SaleQuoteAssetResource defaultQuoteAsset;
    
    public SaleQuoteAssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }
    
    @JsonIgnore
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @JsonIgnore
    @Relationship("quote_assets")
    private List<SaleQuoteAssetResource> quoteAssets;
    
    public List<? extends SaleQuoteAssetResource> getQuoteAssets() {
        return quoteAssets;
    }
}
