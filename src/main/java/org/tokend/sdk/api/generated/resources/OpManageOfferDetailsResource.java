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


@Type("operations-manage-offer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageOfferDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("offer_id")
    @Nullable
    private Long offerId;
    
    @Nullable
    public Long getOfferId() {
        return offerId;
    }
    
    @JsonProperty("order_book_id")
    private Long orderBookId;
    
    public Long getOrderBookId() {
        return orderBookId;
    }
    
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;
    
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("is_buy")
    private Boolean isBuy;
    
    public Boolean isBuy() {
        return isBuy;
    }
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("is_deleted")
    private Boolean isDeleted;
    
    public Boolean isDeleted() {
        return isDeleted;
    }
    
    @Override
    public boolean isFilled() {
        return             orderBookId != null &&
            baseAmount != null &&
            price != null &&
            isBuy != null &&
            fee != null &&
            isDeleted != null 
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
