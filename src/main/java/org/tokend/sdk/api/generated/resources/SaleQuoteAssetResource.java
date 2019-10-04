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


@Type("sale-quote-assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SaleQuoteAssetResource extends BaseResource {
    
    @JsonProperty("current_cap")
    private BigDecimal currentCap;
    
    public BigDecimal getCurrentCap() {
        return currentCap;
    }
    
    @JsonProperty("hard_cap")
    private BigDecimal hardCap;
    
    public BigDecimal getHardCap() {
        return hardCap;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("soft_cap")
    private BigDecimal softCap;
    
    public BigDecimal getSoftCap() {
        return softCap;
    }
    
    @JsonProperty("total_current_cap")
    private BigDecimal totalCurrentCap;
    
    public BigDecimal getTotalCurrentCap() {
        return totalCurrentCap;
    }
    
    @Override
    public boolean isFilled() {
        return             currentCap != null &&
            hardCap != null &&
            price != null &&
            softCap != null &&
            totalCurrentCap != null 
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
