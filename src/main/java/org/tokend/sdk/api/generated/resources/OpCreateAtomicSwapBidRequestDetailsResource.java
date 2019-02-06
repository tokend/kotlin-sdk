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


@Type("operations-create-aswap-bid-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCreateAtomicSwapBidRequestDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("amount")
    private BigDecimal amount;
    
    public BigDecimal getAmount() {
        return amount;
    }
    
    @JsonProperty("details")
    private JsonNode details;
    
    public JsonNode getDetails() {
        return details;
    }
    
    @Override
    public boolean hasAttributes() {
        return             amount != null &&
            details != null 
        ;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
    
    @Relationship("base_balance")
    private BalanceResource baseBalance;
    
    public BalanceResource getBaseBalance() {
        return baseBalance;
    }
    
    @Relationship("quote_assets")
    private List<AssetResource> quoteAssets;
    
    public List<? extends AssetResource> getQuoteAssets() {
        return quoteAssets;
    }
}
