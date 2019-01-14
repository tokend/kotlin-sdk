package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.wallet.xdr.AccountType;

import java.util.List;

@Type("create_account_op_details")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountOperationDetailsResource extends OperationDetailsResource {
    @JsonProperty("funder")
    private String funder;

    @JsonProperty("account")
    private String account;

    @JsonProperty("account_type")
    private Integer accountTypeI;

    @Relationship("participants")
    private List<AccountResource> participants;

    public String getFunder() {
        return funder;
    }

    public String getAccount() {
        return account;
    }

    public Integer getAccountTypeI() {
        return accountTypeI;
    }

    public AccountType getAccountType() {
        for (AccountType type : AccountType.values()) {
            if (type.getValue() == accountTypeI) {
                return type;
            }
        }

        throw new IllegalArgumentException("No corresponding AccountType for " + accountTypeI);
    }

    public List<AccountResource> getParticipants() {
        return participants;
    }

    @Override
    public boolean hasAttributes() {
        return account != null;
    }
}
