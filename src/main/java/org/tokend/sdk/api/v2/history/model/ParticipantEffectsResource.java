package org.tokend.sdk.api.v2.history.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import org.tokend.sdk.api.v2.accounts.model.AccountResource;
import org.tokend.sdk.api.v2.assets.model.AssetResource;
import org.tokend.sdk.api.v2.balances.model.BalanceResource;
import org.tokend.sdk.api.v2.base.BaseResource;
import org.tokend.sdk.api.v2.history.model.effect.EffectResource;
import org.tokend.sdk.api.v2.operations.model.OperationResource;
import org.tokend.sdk.api.v2.operations.model.details.OperationDetailsResource;


@Type("participant-effects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantEffectsResource extends BaseResource {
    @Relationship("account")
    private AccountResource account;

    @Relationship("balance")
    private BalanceResource balance;

    @Relationship("asset")
    private AssetResource asset;

    @Relationship("operation")
    private OperationResource operation;

    @Relationship("operation_details")
    private OperationDetailsResource operationDetails;

    @Relationship("effect")
    private EffectResource effect;

    public AccountResource getAccount() {
        return account;
    }

    public BalanceResource getBalance() {
        return balance;
    }

    public AssetResource getAsset() {
        return asset;
    }

    public OperationResource getOperation() {
        return operation;
    }

    public OperationDetailsResource getOperationDetails() {
        return operationDetails;
    }

    public EffectResource getEffect() {
        return effect;
    }

    @Override
    public boolean hasAttributes() {
        return false;
    }
}
