// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import org.tokend.sdk.api.generated.inner.Enum;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("balances")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResource extends BaseResource {
    @Override
    public boolean isFilled() { return true; }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("owner")
    private AccountResource owner;
    
    public AccountResource getOwner() {
        return owner;
    }
    
    @Relationship("state")
    private BalanceStateResource state;
    
    public BalanceStateResource getState() {
        return state;
    }
}
