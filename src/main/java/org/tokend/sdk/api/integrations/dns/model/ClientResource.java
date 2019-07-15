package org.tokend.sdk.api.integrations.dns.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;
import org.tokend.sdk.api.base.model.BaseResource;
import org.tokend.sdk.api.generated.resources.BalanceResource;

import java.util.List;

@Type("clients")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResource extends BaseResource {

    public ClientResource(String email) {
        this.email = email;
    }

    @JsonProperty("email")
    private String email;

    @JsonProperty("account_id")
    private String accountId;

    @JsonProperty("status")
    private String status;

    @Relationship("balances")
    private List<BalanceResource> balances;

    public String getEmail() {
        return email;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getStatus() {
        return status;
    }

    public List<? extends BalanceResource> getBalances() {
        return balances;
    }

    @Override
    public boolean isFilled() {
        return email != null && accountId != null && status != null;
    }
}
