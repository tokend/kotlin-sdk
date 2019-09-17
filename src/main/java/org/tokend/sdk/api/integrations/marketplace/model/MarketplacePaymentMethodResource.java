package org.tokend.sdk.api.integrations.marketplace.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.BaseResource;

@Type("payment-method")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarketplacePaymentMethodResource extends BaseResource {
    private MarketplacePaymentMethodResource() {
    }

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("destination")
    @Nullable
    private String destination;

    public String getAsset() {
        return asset;
    }

    @Nullable
    public String getDestination() {
        return destination;
    }

    @Override
    public boolean isFilled() {
        return asset != null;
    }
}
