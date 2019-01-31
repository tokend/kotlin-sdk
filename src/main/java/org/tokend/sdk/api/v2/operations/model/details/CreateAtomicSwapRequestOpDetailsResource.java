package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.base.UnknownResource;
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

import java.math.BigDecimal;

@Type("operations-create-atomic-swap-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAtomicSwapRequestOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("base_amount")
    private BigDecimal baseAmount;

    // TODO: Find out type
    @Relationship("bid")
    private UnknownResource bid;

    @Relationship("request")
    private ReviewableRequestResource request;

    @Relationship("quote_asset")
    private AssetResource quoteAsset;

    @Override
    public boolean hasAttributes() {
        return baseAmount != null;
    }
}
