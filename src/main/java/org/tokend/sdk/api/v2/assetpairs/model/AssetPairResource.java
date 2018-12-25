package org.tokend.sdk.api.v2.assetpairs.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.NameValue;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.wallet.xdr.AssetPairPolicy;

import java.math.BigDecimal;
import java.util.List;

@Type("asset_pairs")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssetPairResource extends BaseResource {
    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("policy_i")
    private int policyI;

    @JsonProperty("policies")
    private List<? extends NameValue<Integer>> policies;

    @Relationship("base_asset")
    private AssetResource baseAsset;

    @Relationship("quote_asset")
    private AssetResource quoteAsset;

    public BigDecimal getPrice() {
        return price;
    }

    public int getPolicyI() {
        return policyI;
    }

    public List<? extends NameValue<Integer>> getPolicies() {
        return policies;
    }

    public AssetResource getBaseAsset() {
        return baseAsset;
    }

    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }

    public boolean hasPolicy(AssetPairPolicy policy) {
        return (policyI & policy.getValue()) == policy.getValue();
    }

    @Override
    public boolean hasAttributes() {
        return price != null;
    }
}
