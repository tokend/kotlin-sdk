package org.tokend.sdk.api.v3.fees.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.BaseResource;

import java.math.BigDecimal;

// TODO: Initial model
@Type("exact_fees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExactFeeResource extends BaseResource {
    @JsonProperty("fixed_fee")
    private BigDecimal fixed;

    @JsonProperty("percent_fee")
    private BigDecimal percent;

    @JsonProperty("fee_asset")
    private String assetId;

    public BigDecimal getFixed() {
        return fixed;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public String getAssetId() {
        return assetId;
    }

    @Override
    public boolean hasAttributes() {
        return fixed != null;
    }
}
