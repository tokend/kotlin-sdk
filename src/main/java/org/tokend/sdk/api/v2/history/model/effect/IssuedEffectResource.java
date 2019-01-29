package org.tokend.sdk.api.v2.history.model.effect;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Type;

import java.math.BigDecimal;

@Type("effects-issued")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuedEffectResource extends EffectResource {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("fee")
    private EffectFee fee;

    public BigDecimal getAmount() {
        return amount;
    }

    public EffectFee getFee() {
        return fee;
    }

    @Override
    public boolean hasAttributes() {
        return amount != null;
    }
}
