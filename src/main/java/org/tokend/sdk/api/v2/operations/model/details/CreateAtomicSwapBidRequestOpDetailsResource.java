package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.balances.model.BalanceResource;
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

import java.math.BigDecimal;
import java.util.List;

@Type("operations-create-atomic-swap-bid-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAtomicSwapBidRequestOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("details")
    private JsonNode details;

    @Relationship("request")
    private ReviewableRequestResource request;

    @Relationship("base_balance")
    private BalanceResource baseBalance;

    @Relationship("quote_assets")
    private List<AssetResource> quoteAssets;

    public BigDecimal getAmount() {
        return amount;
    }

    public JsonNode getDetails() {
        return details;
    }

    public ReviewableRequestResource getRequest() {
        return request;
    }

    public BalanceResource getBaseBalance() {
        return baseBalance;
    }

    public List<? extends AssetResource> getQuoteAssets() {
        return quoteAssets;
    }

    @Override
    public boolean hasAttributes() {
        return amount != null;
    }
}
