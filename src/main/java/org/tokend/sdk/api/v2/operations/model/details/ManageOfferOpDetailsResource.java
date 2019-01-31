package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.fees.model.EffectFee;

import java.math.BigDecimal;

@Type("operations-manage-offer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageOfferOpDetailsResource extends OperationDetailsResource {
    @Nullable
    @JsonProperty("offer_id")
    private Long offerId;

    @JsonProperty("order_book_id")
    private Long orderBookId;

    @JsonProperty("base_amount")
    private BigDecimal baseAmount;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("is_buy")
    private Boolean isBuy;

    @JsonProperty("fee")
    private EffectFee fee;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    @Relationship("base_asset")
    private AssetResource baseAsset;

    @Relationship("quote_asset")
    private AssetResource quoteAsset;

    @Nullable
    public Long getOfferId() {
        return offerId;
    }

    public Long getOrderBookId() {
        return orderBookId;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Boolean getBuy() {
        return isBuy;
    }

    public EffectFee getFee() {
        return fee;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public AssetResource getBaseAsset() {
        return baseAsset;
    }

    public AssetResource getQuoteAsset() {
        return quoteAsset;
    }

    @Override
    public boolean hasAttributes() {
        return baseAmount != null;
    }
}
