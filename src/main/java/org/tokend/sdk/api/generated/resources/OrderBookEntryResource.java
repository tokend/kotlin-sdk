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


@Type("order-book-entries")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderBookEntryResource extends BaseResource {
    
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;
    
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }
    
    @JsonProperty("created_at")
    private Date createdAt;
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    @JsonProperty("cumulative_base_amount")
    private BigDecimal cumulativeBaseAmount;
    
    public BigDecimal getCumulativeBaseAmount() {
        return cumulativeBaseAmount;
    }
    
    @JsonProperty("cumulative_quote_amount")
    private BigDecimal cumulativeQuoteAmount;
    
    public BigDecimal getCumulativeQuoteAmount() {
        return cumulativeQuoteAmount;
    }
    
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
    
    @JsonProperty("quote_amount")
    private BigDecimal quoteAmount;
    
    public BigDecimal getQuoteAmount() {
        return quoteAmount;
    }
    
    @Override
    public boolean isFilled() {
        return             baseAmount != null &&
            createdAt != null &&
            cumulativeBaseAmount != null &&
            cumulativeQuoteAmount != null &&
            isBuy != null &&
            price != null &&
            quoteAmount != null 
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
