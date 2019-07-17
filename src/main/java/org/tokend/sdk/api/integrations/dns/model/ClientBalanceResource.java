package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;

import java.math.BigDecimal;

@Type("dns-balances")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientBalanceResource extends BaseResource {
    @JsonProperty("asset")
    private String assetCode;

    @JsonProperty("amount")
    private BigDecimal amount;

    public String getAssetCode() {
        return assetCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean isFilled() {
        return assetCode != null && amount != null;
    }
}
