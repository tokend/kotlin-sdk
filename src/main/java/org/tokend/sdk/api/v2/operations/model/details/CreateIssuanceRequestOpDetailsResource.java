package org.tokend.sdk.api.v2.operations.model.details;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.balances.model.BalanceResource;
import org.tokend.sdk.api.v2.fees.model.EffectFee;
import org.tokend.sdk.api.v2.requests.model.ReviewableRequestResource;

import java.math.BigDecimal;

@Type("operations-create-issuance-request")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateIssuanceRequestOpDetailsResource extends OperationDetailsResource {
    @JsonProperty("fee")
    private EffectFee fee;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("reference")
    private String reference;

    @Nullable
    @JsonProperty("all_tasks")
    private Long allTasks;

    @JsonProperty("external_details")
    private JsonNode externalDetails;

    @Relationship("asset")
    private AssetResource asset;

    @Relationship("request")
    private ReviewableRequestResource request;

    @Relationship("receiver_account")
    private AccountResource receiverAccount;

    @Relationship("receiver_balance")
    private BalanceResource receiverBalance;

    public EffectFee getFee() {
        return fee;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getReference() {
        return reference;
    }

    @Nullable
    public Long getAllTasks() {
        return allTasks;
    }

    public JsonNode getExternalDetails() {
        return externalDetails;
    }

    public AssetResource getAsset() {
        return asset;
    }

    public ReviewableRequestResource getRequest() {
        return request;
    }

    public AccountResource getReceiverAccount() {
        return receiverAccount;
    }

    public BalanceResource getReceiverBalance() {
        return receiverBalance;
    }

    @Override
    public boolean hasAttributes() {
        return fee != null;
    }
}
