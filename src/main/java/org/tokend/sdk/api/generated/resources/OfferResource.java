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


@Type("offers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferResource extends BaseResource {
    
    @JsonProperty("is_buy")
    private Boolean isBuy;
    
    public Boolean isBuy() {
        return isBuy;
    }
    
    @JsonProperty("order_book_id")
    private String orderBookId;
    
    public String getOrderBookId() {
        return orderBookId;
    }
    
    @JsonProperty("created_at")
    private String createdAt;
    
    public String getCreatedAt() {
        return createdAt;
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
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @Override
    public boolean hasAttributes() {
        return             isBuy != null &&
            orderBookId != null &&
            createdAt != null &&
            baseAmount != null &&
            quoteAmount != null &&
            price != null &&
            fee != null 
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
    
    @Relationship("base_balance")
    private BalanceResource baseBalance;
    
    public BalanceResource getBaseBalance() {
        return baseBalance;
    }
    
    @Relationship("quote_asset")
    private AssetResource quoteAsset;
    
    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }
    
    @Relationship("quote_balance")
    private BalanceResource quoteBalance;
    
    public BalanceResource getQuoteBalance() {
        return quoteBalance;
    }
}
