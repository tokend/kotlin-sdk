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


@Type("asset-pairs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetPairResource extends BaseResource {
    
    @JsonProperty("max_price_step")
    private BigDecimal maxPriceStep;
    
    public BigDecimal getMaxPriceStep() {
        return maxPriceStep;
    }
    
    @JsonProperty("physical_price_correction")
    private BigDecimal physicalPriceCorrection;
    
    public BigDecimal getPhysicalPriceCorrection() {
        return physicalPriceCorrection;
    }
    
    @JsonProperty("policies")
    private Mask policies;
    
    public Mask getPolicies() {
        return policies;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public boolean isFilled() {
        return             maxPriceStep != null &&
            physicalPriceCorrection != null &&
            policies != null &&
            price != null 
        ;
    }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("quote_asset")
    private AssetResource quoteAsset;
    
    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }
}
