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


@Type("operations-create-manage-offer-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateManageOfferRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;
    
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }
    
    @JsonProperty("fee")
    private Fee fee;
    
    public Fee getFee() {
        return fee;
    }
    
    @JsonProperty("is_buy")
    private Boolean isBuy;
    
    public Boolean isBuy() {
        return isBuy;
    }
    
    @JsonProperty("offer_id")
    private Long offerId;
    
    public Long getOfferId() {
        return offerId;
    }
    
    @JsonProperty("order_book_id")
    private Long orderBookId;
    
    public Long getOrderBookId() {
        return orderBookId;
    }
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @Override
    public boolean isFilled() {
        return             baseAmount != null &&
            fee != null &&
            isBuy != null &&
            offerId != null &&
            orderBookId != null &&
            price != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}
