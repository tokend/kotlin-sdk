package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.base.model.NameValue;
import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.wallet.xdr.AccountType;

import java.util.List;

@Type("operations-create-account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAccountOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("funder")
    private String funder;

    @JsonProperty("account_address")
    private String accountAddress;

    @JsonProperty("account_type")
    private NameValue<Integer> accountType;

    public String getAccountAddress() {
        return accountAddress;
    }

    public NameValue<Integer> getAccountType() {
        return accountType;
    }

    @Override
    public boolean hasAttributes() {
        return accountAddress != null;
    }
}
