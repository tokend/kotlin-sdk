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


@Type("operations-initiate-kyc-recovery")
@JsonIgnoreProperties(ignoreUnknown = true)
public class InitiateKYCRecoveryOpResource extends BaseOperationDetailsResource {
    
    @JsonProperty("signer")
    private String signer;
    
    public String getSigner() {
        return signer;
    }
    
    @Override
    public boolean isFilled() {
        return             signer != null 
            && super.isFilled()
        ;
    }
    
    @JsonIgnore
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
}
