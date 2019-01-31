package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.balances.model.BalanceResource;

import java.math.BigDecimal;

@Type("operations-create-aml-alert")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateAmlAlertOpDetailsResource  extends OperationDetailsResource {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("reason")
    private String reason;

    @Relationship("balance")
    private BalanceResource balance;

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public BalanceResource getBalance() {
        return balance;
    }

    @Override
    public boolean hasAttributes() {
        return amount != null;
    }
}
