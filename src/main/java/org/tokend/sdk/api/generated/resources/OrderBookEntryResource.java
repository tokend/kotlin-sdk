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


@Type("order-book-entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookEntryResource extends BaseResource {
    
    @JsonProperty("is_buy")
    private Boolean isBuy;
    
    public Boolean isBuy() {
        return isBuy;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;
    
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }
    
    @JsonProperty("quote_amount")
    private BigDecimal quoteAmount;
    
    public BigDecimal getQuoteAmount() {
        return quoteAmount;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @Override
    public boolean hasAttributes() {
        return             isBuy != null &&
            price != null &&
            baseAmount != null &&
            quoteAmount != null &&
            createdAt != null 
        ;
    }
    
    @Relationship("offer")
    private OfferResource offer;
    
    public OfferResource getOffer() {
        return offer;
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
