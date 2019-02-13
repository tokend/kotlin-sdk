package org.tokend.sdk.api.v3.fees.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;

public class FeeTarget {
    @JsonProperty("asset")
    private String asset;

    @JsonProperty("fee_type")
    private int type;

    @JsonProperty("subtype")
    private int subtype;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("account_type")
    private int accountType;

    @JsonProperty("lower_bound")
    private BigDecimal lowerBound;

    @JsonProperty("upper_bound")
    private BigDecimal upperBound;

    public String getAsset() {
        return asset;
    }

    public int getType() {
        return type;
    }

    public int getSubtype() {
        return subtype;
    }

    @Nullable
    public String getAccountId() {
        return accountId == null || accountId.isEmpty() ? null : accountId;
    }

    public int getAccountType() {
        return accountType;
    }

    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }
}
