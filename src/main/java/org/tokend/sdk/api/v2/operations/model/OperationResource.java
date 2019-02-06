package org.tokend.sdk.api.v2.operations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.base.model.BaseResource;
import org.tokend.sdk.api.v2.operations.model.details.OperationDetailsResource;
import org.tokend.sdk.api.v2.transactions.model.TransactionResource;

import java.util.Date;

@Type("operations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationResource extends BaseResource {

    @JsonProperty("applied_at")
    private Date appliedAt;

    @Relationship("tx")
    private TransactionResource transaction;

    @Relationship("source")
    private AccountResource sourceAccount;

    @Relationship("details")
    private OperationDetailsResource details;

    public Date getAppliedAt() {
        return appliedAt;
    }

    public TransactionResource getTransaction() {
        return transaction;
    }

    public AccountResource getSourceAccount() {
        return sourceAccount;
    }

    public <T extends OperationDetailsResource> T getDetails() {
        return (T) details;
    }

    @Override
    public boolean hasAttributes() {
        return appliedAt != null;
    }
}
