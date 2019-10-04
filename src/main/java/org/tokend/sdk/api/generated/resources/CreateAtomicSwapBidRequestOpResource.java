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


@Type("operations-create-atomic-swap-bid-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAtomicSwapBidRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;
    
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @Override
    public boolean isFilled() {
        return             baseAmount != null &&
            creatorDetails != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("ask")
    private AtomicSwapAskResource ask;
    
    public AtomicSwapAskResource getAsk() {
        return ask;
    }
    
    @Relationship("quote_asset")
    private AssetResource quoteAsset;
    
    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}
