package org.tokend.sdk.api.v3.fees.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Describes fee happened on balance. Direction of fee depends on the operation
 * (depending on effect might be charged, locked, unlocked, for all incoming effects
 * but unlocked it's always charged)
 */
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

    public BigDecimal getTotal() {
        return fixed.add(calculatedPercent);
    }
}
