package org.tokend.sdk.api.v2.assets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.RelationshipLinks;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.assets.model.AssetDetails;
import org.tokend.sdk.api.base.model.NameValue;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.sdk.api.v2.base.UnknownResource;

import java.math.BigDecimal;
import java.util.List;

@Type("assets")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetResource extends BaseResource {
    @JsonProperty("available_for_issuance")
    private BigDecimal availableForIssuance;

    @JsonProperty("preissued_asset_signer")
    private String preissuedAssetSigner;

    @JsonProperty("max_issuance_amount")
    private BigDecimal maxIssuanceAmount;

    @JsonProperty("issued")
    private BigDecimal issued;

    @JsonProperty("pending_issuance")
    private BigDecimal pendingIssuance;

    @JsonProperty("policy_i")
    private int policyI;

    @JsonProperty("policies")
    private List<? extends NameValue<Integer>> policies;

    @JsonProperty("details")
    private AssetDetails details;

    /**
     * Required for links parsing.
     */
    @Relationship("logo")
    private UnknownResource logo;

    @RelationshipLinks("logo")
    private Links logoLinks;

    /**
     * Required for links parsing.
     */
    @Relationship("terms")
    private UnknownResource terms;

    @RelationshipLinks("terms")
    private Links termsLinks;

    @Relationship("owner")
    private UnknownResource owner;

    public BigDecimal getAvailableForIssuance() {
        return availableForIssuance;
    }

    public String getPreissuedAssetSigner() {
        return preissuedAssetSigner;
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

    public int getPolicyI() {
        return policyI;
    }

    public List<? extends NameValue<Integer>> getPolicies() {
        return policies;
    }

    public AssetDetails getDetails() {
        return details;
    }

    public Links getLogoLinks() {
        return logoLinks;
    }

    public Links getTermsLinks() {
        return termsLinks;
    }

    public UnknownResource getOwner() {
        return owner;
    }

    @Override
    public boolean hasAttributes() {
        return availableForIssuance != null;
    }
}
