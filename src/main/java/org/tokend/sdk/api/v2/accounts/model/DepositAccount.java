package org.tokend.sdk.api.v2.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositAccount {

    @JsonProperty("address")
    private String address;

    @JsonProperty("expires_at")
    private String expires_at;

    @JsonProperty("type")
    private String type;

    public String getAddress() {
        return address;
    }

    public String getExpires_at() {
        return expires_at;
    }

    public String getType() {
        return type;
    }
}
