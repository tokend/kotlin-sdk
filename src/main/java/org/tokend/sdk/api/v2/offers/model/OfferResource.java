package org.tokend.sdk.api.v2.offers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.balances.model.BalanceResource;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.sdk.api.v2.fees.model.EffectFee;

import java.math.BigDecimal;
import java.util.Date;

@Type("offers")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferResource extends BaseResource {
    @JsonProperty("order_book_id")
    private long orderBookId;

    @JsonProperty("fee")
    private EffectFee fee;

    @JsonProperty("is_buy")
    private boolean isBuy;

    @JsonProperty("base_amount")
    private BigDecimal baseAmount;

    @JsonProperty("quote_amount")
    private BigDecimal quoteAmount;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("created_at")
    private Date createdAt;

    @Relationship("base_balance")
    private BalanceResource baseBalance;

    @Relationship("quote_balance")
    private BalanceResource quoteBalance;

    @Relationship("base_asset")
    private AssetResource baseAsset;

    @Relationship("quote_asset")
    private AssetResource quoteAsset;

    @Relationship("owner")
    private AccountResource owner;

    public long getOrderBookId() {
        return orderBookId;
    }

    public EffectFee getFee() {
        return fee;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public BigDecimal getQuoteAmount() {
        return quoteAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public BalanceResource getBaseBalance() {
        return baseBalance;
    }

    public BalanceResource getQuoteBalance() {
        return quoteBalance;
    }

    public AssetResource getBaseAsset() {
        return baseAsset;
    }

    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }

    public AccountResource getOwner() {
        return owner;
    }

    @Override
    public boolean hasAttributes() {
        return createdAt != null;
    }
}
