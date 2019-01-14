package org.tokend.sdk.api.v2.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.jetbrains.annotations.Nullable;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositAccount {

    @JsonProperty("address")
    private String address;

    @JsonProperty("expires_at")
    @Nullable
    private Date expires_at;

    @JsonProperty("type")
    private String type;

    public String getAddress() {
        return address;
    }

    @Nullable
    public Date getExpiresAt() {
        return expires_at;
    }

    public String getType() {
        return type;
    }
}
