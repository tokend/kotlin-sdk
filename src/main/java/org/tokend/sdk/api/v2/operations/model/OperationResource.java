package org.tokend.sdk.api.v2.operations.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.sdk.api.v2.operations.model.details.OperationDetailsResource;
import org.tokend.wallet.xdr.OperationType;

import java.util.Date;

@Type("operations")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OperationResource extends BaseResource {
    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("operation_type")
    private String operationType;

    @JsonProperty("operation_type_i")
    private Integer operationTypeI;

    @JsonProperty("state")
    private String state;

    @JsonProperty("state_i")
    private Integer stateI;

    @JsonProperty("identifier")
    private String identifier;

    @JsonProperty("ledger_close_time")
    private Date ledgerCloseTime;

    @Relationship("source")
    private AccountResource sourceAccount;

    @Relationship("operation_details")
    private OperationDetailsResource details;

    public String getTransactionId() {
        return transactionId;
    }

    public OperationType getOperationType() {
        for (OperationType type : OperationType.values()) {
            if (type.getValue() == operationTypeI) {
                return type;
            }
        }

        throw new IllegalArgumentException("No corresponding OperationType for " + operationTypeI);
    }

    public String getOperationTypeString() {
        return operationType;
    }

    public Integer getOperationTypeI() {
        return operationTypeI;
    }

    public String getStateString() {
        return state;
    }

    public Integer getStateI() {
        return stateI;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Date getLedgerCloseTime() {
        return ledgerCloseTime;
    }

    public AccountResource getSourceAccount() {
        return sourceAccount;
    }

    public <T extends OperationDetailsResource> T getDetails() {
        return (T) details;
    }

    @Override
    public boolean hasAttributes() {
        return operationType != null;
    }
}
