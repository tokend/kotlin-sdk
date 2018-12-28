package org.tokend.sdk.api.v2.requests.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.assets.model.AssetDetails;
import org.tokend.sdk.api.base.model.NameValue;

import java.math.BigDecimal;
import java.util.List;

@Type("asset_create_request_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetCreateRequestDetailsResource extends ReviewableRequestDetailsResource {
    @JsonProperty("code")
    private String code;

    @JsonProperty("policies")
    private List<? extends NameValue<Integer>> policies;

    @JsonProperty("pre_issued_asset_signer")
    private String preissuedAssetSigner;

    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;

    @JsonProperty("initial_preissued_amount")
    private BigDecimal initialPreissuedAmount;

    @JsonProperty("details")
    private AssetDetails details;

    public List<? extends NameValue<Integer>> getPolicies() {
        return policies;
    }

    public String getPreissuedAssetSigner() {
        return preissuedAssetSigner;
    }

    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }

    public BigDecimal getInitialPreissuedAmount() {
        return initialPreissuedAmount;
    }

    public AssetDetails getDetails() {
        return details;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean hasAttributes() {
        return code != null;
    }
}
