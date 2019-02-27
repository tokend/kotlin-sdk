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


@Type("operations-manage-asset-pair")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageAssetPairDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("physical_price")
    private BigDecimal physicalPrice;
    
    public BigDecimal getPhysicalPrice() {
        return physicalPrice;
    }
    
    @JsonProperty("physical_price_correction")
    private BigDecimal physicalPriceCorrection;
    
    public BigDecimal getPhysicalPriceCorrection() {
        return physicalPriceCorrection;
    }
    
    @JsonProperty("max_price_step")
    private BigDecimal maxPriceStep;
    
    public BigDecimal getMaxPriceStep() {
        return maxPriceStep;
    }
    
    @JsonProperty("policies")
    private XdrEnumBitmask policies;
    
    public XdrEnumBitmask getPolicies() {
        return policies;
    }
    
    @Override
    public boolean isFilled() {
        return             physicalPrice != null &&
            physicalPriceCorrection != null &&
            maxPriceStep != null &&
            policies != null 
            && super.isFilled()
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
