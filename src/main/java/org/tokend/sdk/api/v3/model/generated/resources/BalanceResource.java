// Auto-generated code. Do not edit.

package org.tokend.sdk.api.v3.model.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.v3.model.generated.resources.*;
import org.tokend.sdk.api.v3.model.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("balances")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @JsonIgnore
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @JsonIgnore
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @JsonIgnore
    @Relationship("state")
    private BalanceStateResource state;
    
    public BalanceStateResource getState() {
        return state;
    }
}
