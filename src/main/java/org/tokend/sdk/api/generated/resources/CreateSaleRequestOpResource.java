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


@Type("operations-create-sale-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSaleRequestOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("creator_details")
    private JsonNode creatorDetails;
    
    public JsonNode getCreatorDetails() {
        return creatorDetails;
    }
    
    @JsonProperty("end_time")
    private Date endTime;
    
    public Date getEndTime() {
        return endTime;
    }
    
    @JsonProperty("hard_cap")
    private BigDecimal hardCap;
    
    public BigDecimal getHardCap() {
        return hardCap;
    }
    
    @JsonProperty("soft_cap")
    private BigDecimal softCap;
    
    public BigDecimal getSoftCap() {
        return softCap;
    }
    
    @JsonProperty("start_time")
    private Date startTime;
    
    public Date getStartTime() {
        return startTime;
    }
    
    @Override
    public boolean isFilled() {
        return             creatorDetails != null &&
            endTime != null &&
            hardCap != null &&
            softCap != null &&
            startTime != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("base_asset")
    private AssetResource baseAsset;
    
    public AssetResource getBaseAsset() {
        return baseAsset;
    }
    
    @Relationship("default_quote_asset")
    private AssetResource defaultQuoteAsset;
    
    public AssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }
    
    @Relationship("quote_assets")
    private List<AssetResource> quoteAssets;
    
    public List<? extends AssetResource> getQuoteAssets() {
        return quoteAssets;
    }
    
    @Relationship("request")
    private ReviewableRequestResource request;
    
    public ReviewableRequestResource getRequest() {
        return request;
    }
}
