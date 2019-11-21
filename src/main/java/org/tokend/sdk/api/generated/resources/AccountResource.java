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
    @Nullable
    private org.tokend.sdk.api.generated.inner.Enum kycRecoveryStatus;
    
    @Nullable
    public org.tokend.sdk.api.generated.inner.Enum getKycRecoveryStatus() {
        return kycRecoveryStatus;
    }
    
    @Override
    public boolean isFilled() {
        return             kycRecoveryStatus != null 
        ;
    }
    
    @Relationship("balances")
    private List<BalanceResource> balances;
    
    public List<? extends BalanceResource> getBalances() {
        return balances;
    }
    
    @Relationship("external_system_ids")
    private List<ExternalSystemIDResource> externalSystemIds;
    
    public List<? extends ExternalSystemIDResource> getExternalSystemIds() {
        return externalSystemIds;
    }
    
    @Relationship("fees")
    private List<FeeRecordResource> fees;
    
    public List<? extends FeeRecordResource> getFees() {
        return fees;
    }
    
    @Relationship("kyc_data")
    private AccountKYCResource kycData;
    
    public AccountKYCResource getKycData() {
        return kycData;
    }
    
    @Relationship("limits")
    private List<LimitsResource> limits;
    
    public List<? extends LimitsResource> getLimits() {
        return limits;
    }
    
    @Relationship("limits_with_stats")
    private List<LimitsWithStatsResource> limitsWithStats;
    
    public List<? extends LimitsWithStatsResource> getLimitsWithStats() {
        return limitsWithStats;
    }
    
    @Relationship("referrer")
    private AccountResource referrer;
    
    public AccountResource getReferrer() {
        return referrer;
    }
    
    @Relationship("role")
    private AccountRoleResource role;
    
    public AccountRoleResource getRole() {
        return role;
    }
}
