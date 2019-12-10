// Auto-generated code. Do not edit.

package org.tokend.sdk.api.ingester.generated.resources;

import java.math.*;
import java.util.*;
import com.fasterxml.jackson.annotation.*;
import com.github.jasminb.jsonapi.annotations.*;
import org.tokend.sdk.api.ingester.generated.resources.*;
import org.tokend.sdk.api.ingester.generated.inner.*;
import com.fasterxml.jackson.databind.*;
import org.jetbrains.annotations.Nullable;
import org.tokend.sdk.api.base.model.*;


@Type("accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResource extends BaseResource {
    
    @JsonProperty("kyc_recovery_status")
    @Nullable
    private org.tokend.sdk.api.ingester.generated.inner.Enum kycRecoveryStatus;
    
    @Nullable
    public org.tokend.sdk.api.ingester.generated.inner.Enum getKycRecoveryStatus() {
        return kycRecoveryStatus;
    }
    
    @Override
    public boolean isFilled() {
        return             kycRecoveryStatus != null 
        ;
    }
    
    @Relationship("kyc_data")
    private AccountKYCResource kycData;
    
    public AccountKYCResource getKycData() {
        return kycData;
    }
    
    @Relationship("referrer")
    private AccountResource referrer;
    
    public AccountResource getReferrer() {
        return referrer;
    }
    
    @Relationship("roles")
    private List<RoleResource> roles;
    
    public List<? extends RoleResource> getRoles() {
        return roles;
    }
}
