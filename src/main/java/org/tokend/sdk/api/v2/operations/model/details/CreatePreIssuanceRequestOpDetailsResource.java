package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

import java.math.BigDecimal;

@Type("operations-create-preissuance-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePreIssuanceRequestOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("amount")
    private BigDecimal amount;

    @Relationship("asset")
    private AssetResource asset;

    @Relationship("request")
    private ReviewableRequestResource request;

    public BigDecimal getAmount() {
        return amount;
    }

    public AssetResource getAsset() {
        return asset;
    }

    public ReviewableRequestResource getRequest() {
        return request;
    }

    @Override
    public boolean hasAttributes() {
        return amount != null;
    }
}
