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


@Type("effects-matched")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EffectMatchedResource extends BaseEffectResource {
    
    @JsonProperty("charged")
    private ParticularBalanceChangeEffect charged;
    
    public ParticularBalanceChangeEffect getCharged() {
        return charged;
    }
    
    @JsonProperty("funded")
    private ParticularBalanceChangeEffect funded;
    
    public ParticularBalanceChangeEffect getFunded() {
        return funded;
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
        return             charged != null &&
            funded != null &&
            offerId != null &&
            orderBookId != null &&
            price != null 
            && super.isFilled()
        ;
    }
}
