// Auto-generated code. Do not edit.

package org.tokend.sdk.api.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.generated.*;
import org.tokend.sdk.api.generated.resources.*;
import org.tokend.sdk.api.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("operations-manage-balance")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageBalanceDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("action")
    private XdrEnumValue action;
    
    public XdrEnumValue getAction() {
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
    
    @Relationship("destination_account")
    private AccountResource destinationAccount;
    
    public AccountResource getDestinationAccount() {
        return destinationAccount;
    }
    
    @Relationship("asset")
    private AssetResource asset;
    
    public AssetResource getAsset() {
        return asset;
    }
}
