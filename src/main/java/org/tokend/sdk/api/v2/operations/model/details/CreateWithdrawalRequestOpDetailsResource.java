package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.balances.model.BalanceResource;
import org.tokend.sdk.api.v2.fees.model.EffectFee;

import java.math.BigDecimal;

@Type("operations-create-withdrawal-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateWithdrawalRequestOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("fee")
    private EffectFee fee;

    @JsonProperty("external_details")
    private JsonNode externalDetails;

    @Relationship("balance")
    private BalanceResource balance;

    public BigDecimal getAmount() {
        return amount;
    }

    public EffectFee getFee() {
        return fee;
    }

    public JsonNode getExternalDetails() {
        return externalDetails;
    }

    public BalanceResource getBalance() {
        return balance;
    }

    @Override
    public boolean hasAttributes() {
        return amount != null;
    }
}
