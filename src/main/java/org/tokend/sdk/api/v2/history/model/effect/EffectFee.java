package org.tokend.sdk.api.v2.history.model.effect;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

//TODO: find out what it should be after api refactoring
public class EffectFee {

    @JsonProperty("fixed")
    private BigDecimal fixed;

    @JsonProperty("calculated_percent")
    private BigDecimal calculatedPercent;

    public BigDecimal getFixed() {
        return fixed;
    }

    public BigDecimal getCalculatedPercent() {
        return calculatedPercent;
    }
}
