package org.tokend.sdk.api.v2.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Thresholds {

    @JsonProperty("low_threshold")
    private BigDecimal lowThreshold;

    @JsonProperty("med_threshold")
    private BigDecimal medThreshold;

    @JsonProperty("high_threshold")
    private BigDecimal highTreshold;

    public BigDecimal getLowThreshold() {
        return lowThreshold;
    }

    public BigDecimal getMedThreshold() {
        return medThreshold;
    }

    public BigDecimal getHighThreshold() {
        return highTreshold;
    }
}
