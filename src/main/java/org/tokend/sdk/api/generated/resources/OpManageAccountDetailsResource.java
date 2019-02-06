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


@Type("operations-manage-account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpManageAccountDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("account_address")
    private XdrEnumValue accountAddress;
    
    public XdrEnumValue getAccountAddress() {
        return accountAddress;
    }
    
    @JsonProperty("block_reasons_to_add")
    private XdrEnumBitmask blockReasonsToAdd;
    
    public XdrEnumBitmask getBlockReasonsToAdd() {
        return blockReasonsToAdd;
    }
    
    @JsonProperty("block_reasons_to_remove")
    private XdrEnumBitmask blockReasonsToRemove;
    
    public XdrEnumBitmask getBlockReasonsToRemove() {
        return blockReasonsToRemove;
    }
    
    @Override
    public boolean hasAttributes() {
        return             accountAddress != null &&
            blockReasonsToAdd != null &&
            blockReasonsToRemove != null 
        ;
    }
}
