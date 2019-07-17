package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.BaseResource;

import java.util.List;

@Type("clients")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResource extends BaseResource {

    private ClientResource() {
    }

    public ClientResource(String email) {
        this.email = email;
    }

    @JsonProperty("email")
    private String email;

    @JsonProperty("account_id")
    @Nullable
    private String accountId;

    @JsonProperty("status")
    private String status;

    @Relationship("balances")
    @Nullable
    private List<ClientBalanceResource> balances;

    public String getEmail() {
        return email;
    }

    @Nullable
    public String getAccountId() {
        return accountId;
    }

    public String getStatus() {
        return status;
    }

    @Nullable
    public List<? extends ClientBalanceResource> getBalances() {
        return balances;
    }

    @Override
    public boolean isFilled() {
        return email != null && accountId != null && status != null;
    }
}
