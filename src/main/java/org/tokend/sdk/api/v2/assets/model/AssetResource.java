package org.tokend.sdk.api.v2.assets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.assets.model.AssetDetails;
import org.tokend.sdk.api.base.model.Policies;
import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.base.model.BaseResource;
import org.tokend.wallet.xdr.AssetPolicy;

import java.math.BigDecimal;

@Type("assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResource extends BaseResource {
    @JsonProperty("available_for_issuance")
    private BigDecimal availableForIssuance;

    @JsonProperty("pre_issuance_asset_signer")
    private String preIssuanceAssetSigner;

    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;

    @JsonProperty("issued")
    private BigDecimal issued;

    @JsonProperty("pending_issuance")
    private BigDecimal pendingIssuance;

    @JsonProperty("policies")
    private Policies policies;

    @JsonProperty("details")
    private AssetDetails details;

    @JsonProperty("trailing_digits")
    private int trailingDigits;

    @Relationship("owner")
    private AccountResource owner;

    public BigDecimal getAvailableForIssuance() {
        return availableForIssuance;
    }

    public String getPreIssuanceAssetSigner() {
        return preIssuanceAssetSigner;
    }

    public BigDecimal getMaxIssuanceAmount() {
        return maxIssuanceAmount;
    }

    public BigDecimal getIssued() {
        return issued;
    }

    public BigDecimal getPendingIssuance() {
        return pendingIssuance;
    }

    public Policies getPolicies() {
        return policies;
    }

    public AssetDetails getDetails() {
        return details;
    }

    public AccountResource getOwner() {
        return owner;
    }

    public int getTrailingDigits() {
        return trailingDigits;
    }

    public boolean isBackedByExternalSystem() {
        return details.getExternalSystemType() != null;
    }

    public boolean hasPolicy(AssetPolicy policy) {
        return policies.have(policy.getValue());
    }

    @Override
    public boolean hasAttributes() {
        return availableForIssuance != null;
    }
}
