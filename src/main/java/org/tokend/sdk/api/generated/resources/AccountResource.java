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


@Type("accounts")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountResource extends BaseResource {
    
    @JsonProperty("kyc_recovery_status")
    private XdrEnumValue kycRecoveryStatus;
    
    public XdrEnumValue getKycRecoveryStatus() {
        return kycRecoveryStatus;
    }
    
    @Override
    public boolean isFilled() {
        return             kycRecoveryStatus != null 
        ;
    }
    
    @Relationship("role")
    private AccountRoleResource role;
    
    public AccountRoleResource getRole() {
        return role;
    }
    
    @Relationship("balances")
    private List<BalanceResource> balances;
    
    public List<? extends BalanceResource> getBalances() {
        return balances;
    }
    
    @Relationship("referrer")
    private AccountResource referrer;
    
    public AccountResource getReferrer() {
        return referrer;
    }
    
    @Relationship("limits")
    private List<LimitResource> limits;
    
    public List<? extends LimitResource> getLimits() {
        return limits;
    }
    
    @Relationship("external_system_ids")
    private List<ExternalSystemIdResource> externalSystemIds;
    
    public List<? extends ExternalSystemIdResource> getExternalSystemIds() {
        return externalSystemIds;
    }
    
    @Relationship("fees")
    private List<FeeResource> fees;
    
    public List<? extends FeeResource> getFees() {
        return fees;
    }
}
