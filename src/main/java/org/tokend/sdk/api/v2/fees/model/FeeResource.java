package org.tokend.sdk.api.v2.fees.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.base.BaseResource;

import java.math.BigDecimal;

@Type("fees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeResource extends BaseResource {
    @JsonProperty("fixed")
    private BigDecimal fixed;

    @JsonProperty("percent")
    private BigDecimal percent;

    @JsonProperty("applied_to")
    private FeeTarget appliedTo;

    @Nullable
    @Relationship("account")
    private AccountResource account;

    @Relationship("asset")
    private AssetResource asset;

    @Relationship("fee_asset")
    private AssetResource feeAsset;

    public BigDecimal getFixed() {
        return fixed;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public FeeTarget getAppliedTo() {
        return appliedTo;
    }

    @Nullable
    public AccountResource getAccount() {
        return account;
    }

    public AssetResource getAsset() {
        return asset;
    }

    public AssetResource getFeeAsset() {
        return feeAsset;
    }

    @Override
    public boolean hasAttributes() {
        return fixed != null;
    }
}
