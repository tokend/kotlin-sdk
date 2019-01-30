package org.tokend.sdk.api.v2.history.model.effect;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.tokend.sdk.api.v2.fees.model.EffectFee;

import java.math.BigDecimal;

//TODO: determine what is it and rename
public class RenameMeEntity {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("fee")
    private EffectFee fee;

    @JsonProperty("balance_address")
    private String balanceAddress;

    @JsonProperty("asset_code")
    private String assetCode;

    public BigDecimal getAmount() {
        return amount;
    }

    public EffectFee getFee() {
        return fee;
    }

    public String getBalanceAddress() {
        return balanceAddress;
    }

    public String getAssetCode() {
        return assetCode;
    }
}
