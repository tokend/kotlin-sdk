package org.tokend.sdk.api.v2.balances.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.base.model.BaseResource;

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

    @Relationship("account")
    private AccountResource account;

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

    public AccountResource getAccount() {
        return account;
    }

    @Override
    public boolean hasAttributes() {
        return available != null;
    }
}
