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


@Type("atomic-swap-quote-assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtomicSwapQuoteAssetResource extends BaseResource {
    
    @JsonProperty("price")
    private BigDecimal price;
    
    public BigDecimal getPrice() {
        return price;
    }
    
    @JsonProperty("quote_asset")
    private String quoteAsset;
    
    public String getQuoteAsset() {
        return quoteAsset;
    }
    
    @Override
    public boolean isFilled() {
        return             price != null &&
            quoteAsset != null 
        ;
    }
}
