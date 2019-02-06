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


@Type("operations-create-account")
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpCreateAccountDetailsResource extends OperationDetailsResource {
    
    @JsonProperty("account_address")
    private String accountAddress;
    
    public String getAccountAddress() {
        return accountAddress;
    }
    
    @JsonProperty("account_type")
    private XdrEnumValue accountType;
    
    public XdrEnumValue getAccountType() {
        return accountType;
    }
    
    @Override
    public boolean hasAttributes() {
        return             accountAddress != null &&
            accountType != null 
        ;
    }
}
