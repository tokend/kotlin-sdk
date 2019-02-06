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


@Type("asset-pairs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetPairResource extends BaseResource {
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("policies")
    private XdrEnumBitmask policies;
    
    public XdrEnumBitmask getPolicies() {
        return policies;
    }
    
    @Override
    public boolean hasAttributes() {
        return             price != null &&
            policies != null 
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
