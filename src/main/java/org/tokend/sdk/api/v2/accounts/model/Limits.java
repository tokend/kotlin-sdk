package org.tokend.sdk.api.v2.accounts.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Limits {

    @JsonProperty("daily_out")
    private BigDecimal dailyOut;

    @JsonProperty("weekly_out")
    private BigDecimal weeklyOut;

    @JsonProperty("monthly_out")
    private BigDecimal monthlyOut;

    @JsonProperty("annual_out")
    private BigDecimal annualOut;

    public BigDecimal getDailyOut() {
        return dailyOut;
    }

    public BigDecimal getWeeklyOut() {
        return weeklyOut;
    }

    public BigDecimal getMonthlyOut() {
        return monthlyOut;
    }

    public BigDecimal getAnnualOut() {
        return annualOut;
    }
}
