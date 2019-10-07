package org.tokend.sdk.api.integrations.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

import java.math.BigDecimal;
import java.util.List;

@Type("marketplace-offer")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketplaceOfferResource extends BaseResource {
    private MarketplaceOfferResource() { }

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("price_asset")
    private String priceAsset;

    @JsonProperty("base_amount")
    private BigDecimal baseAmount;

    @JsonProperty("locked")
    private BigDecimal locked;

    @JsonProperty("base_asset")
    private String baseAsset;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("is_canceled")
    private boolean isCanceled;

    @Relationship("payment_methods")
    private List<MarketplacePaymentMethodResource> paymentMethods;

    public BigDecimal getPrice() {
        return price;
    }

    public String getPriceAsset() {
        return priceAsset;
    }

    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    public BigDecimal getLocked() {
        return locked;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public String getOwner() {
        return owner;
    }

    public boolean isCanceled() {
        return isCanceled;
    }

    public List<? extends MarketplacePaymentMethodResource> getPaymentMethods() {
        return paymentMethods;
    }

    @Override
    public boolean isFilled() {
        return price != null && priceAsset != null && baseAmount != null
                && locked != null && baseAsset != null && owner != null;
    }
}
