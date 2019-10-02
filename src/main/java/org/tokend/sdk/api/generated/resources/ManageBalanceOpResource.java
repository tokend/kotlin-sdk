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


@Type("operations-manage-balance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ManageBalanceOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("action")
    private Enum action;
    
    public Enum getAction() {
        return action;
    }
    
    @JsonProperty("balance_address")
    private String balanceAddress;
    
    public String getBalanceAddress() {
        return balanceAddress;
    }
    
    @Override
    public boolean isFilled() {
        return             action != null &&
            balanceAddress != null 
            && super.isFilled()
        ;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
    
    @Relationship("destination_account")
    private AccountResource destinationAccount;
    
    public AccountResource getDestinationAccount() {
        return destinationAccount;
    }
}
