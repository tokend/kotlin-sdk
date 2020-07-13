// Auto-generated code. Do not edit.

package org.tokend.sdk.api.integrations.exchange.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.integrations.exchange.model.generated.resources.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("currency-exchange-rates")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRateResource extends BaseResource {
    
    @JsonProperty("last_updated")
    private Date lastUpdated;
    
    public Date getLastUpdated() {
        return lastUpdated;
    }
    
    @JsonProperty("rate")
    private BigDecimal rate;
    
    public BigDecimal getRate() {
        return rate;
    }
    
    @Override
    public boolean isFilled() {
        return             lastUpdated != null &&
            rate != null 
        ;
    }
    
    @Relationship("base")
    private BaseResource base;
    
    public BaseResource getBase() {
        return base;
    }
    
    @Relationship("quote")
    private BaseResource quote;
    
    public BaseResource getQuote() {
        return quote;
    }
}
