package org.tokend.sdk.api.v2.balances.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.Links;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.RelationshipLinks;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.base.BaseResource;

import java.math.BigDecimal;

@Type("balances")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResource extends BaseResource {

    @JsonProperty("available")
    private BigDecimal available;

    @JsonProperty("locked")
    private BigDecimal locked;

    @JsonProperty("require_review")
    private boolean requireReview;

    @Relationship("asset")
    private AssetResource asset;

    @RelationshipLinks("asset")
    private Links assetLinks;

    @Relationship("account")
    private AccountResource account;

    @RelationshipLinks("account")
    private Links accountLinks;

    public BigDecimal getAvailable() {
        return available;
    }

    public BigDecimal getLocked() {
        return locked;
    }

    public boolean isRequireReview() {
        return requireReview;
    }

    public AssetResource getAsset() {
        return asset;
    }

    public Links getAssetLinks() {
        return assetLinks;
    }

    public AccountResource getAccount() {
        return account;
    }

    public Links getAccountLinks() {
        return accountLinks;
    }

    @Override
    public boolean hasAttributes() {
        return available != null;
    }
}
