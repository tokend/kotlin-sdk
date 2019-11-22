// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("participant-effects")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParticipantsEffectResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("balance")
    private BalanceResource balance;
    
    public BalanceResource getBalance() {
        return balance;
    }
    
    @Relationship("effect")
    private BaseEffectResource effect;
    
    public BaseEffectResource getEffect() {
        return effect;
    }
    
    @Relationship("operation")
    private OperationResource operation;
    
    public OperationResource getOperation() {
        return operation;
    }
}
