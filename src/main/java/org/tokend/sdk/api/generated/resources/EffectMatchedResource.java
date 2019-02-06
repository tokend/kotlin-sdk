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


@Type("effects-matched")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EffectMatchedResource extends EffectResource {
    
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
    
    @JsonProperty("charged")
    private ParticularBalanceChange charged;
    
    public ParticularBalanceChange getCharged() {
        return charged;
    }
    
    @JsonProperty("funded")
    private ParticularBalanceChange funded;
    
    public ParticularBalanceChange getFunded() {
        return funded;
    }
    
    @Override
    public boolean hasAttributes() {
        return             offerId != null &&
            orderBookId != null &&
            price != null &&
            charged != null &&
            funded != null 
        ;
    }
}
