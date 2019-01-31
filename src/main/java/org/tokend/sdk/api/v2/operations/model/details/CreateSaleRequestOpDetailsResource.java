package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Type("operations-create-sale-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSaleRequestOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("start_time")
    private Date startTime;

    @JsonProperty("end_time")
    private Date endTime;

    @JsonProperty("soft_cap")
    private BigDecimal softCap;

    @JsonProperty("hard_cap")
    private BigDecimal hardCap;

    // TODO: Find out type
    @JsonProperty("details")
    private JsonNode details;

    @Relationship("request")
    private ReviewableRequestResource request;

    @Relationship("quote_assets")
    private List<AssetResource> quoteAssets;

    @Relationship("base_asset")
    private AssetResource baseAsset;

    @Relationship("default_quote_asset")
    private AssetResource defaultQuoteAsset;

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public BigDecimal getSoftCap() {
        return softCap;
    }

    public BigDecimal getHardCap() {
        return hardCap;
    }

    public JsonNode getDetails() {
        return details;
    }

    public ReviewableRequestResource getRequest() {
        return request;
    }

    public List<? extends AssetResource> getQuoteAssets() {
        return quoteAssets;
    }

    public AssetResource getBaseAsset() {
        return baseAsset;
    }

    public AssetResource getDefaultQuoteAsset() {
        return defaultQuoteAsset;
    }

    @Override
    public boolean hasAttributes() {
        return startTime != null;
    }
}
