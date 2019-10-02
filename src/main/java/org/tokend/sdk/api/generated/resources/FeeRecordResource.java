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


@Type("fees")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FeeRecordResource extends BaseResource {
    
    @JsonProperty("applied_to")
    private FeeAppliedTo appliedTo;
    
    public FeeAppliedTo getAppliedTo() {
        return appliedTo;
    }
    
    @JsonProperty("fixed")
    private BigDecimal fixed;
    
    public BigDecimal getFixed() {
        return fixed;
    }
    
    @JsonProperty("percent")
    private BigDecimal percent;
    
    public BigDecimal getPercent() {
        return percent;
    }
    
    @Override
    public boolean isFilled() {
        return             appliedTo != null &&
            fixed != null &&
            percent != null 
        ;
    }
    
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
    
    @Relationship("account_role")
    private AccountRoleResource accountRole;
    
    public AccountRoleResource getAccountRole() {
        return accountRole;
    }
    
    @Relationship("asset")
    private AccountResource asset;
    
    public AccountResource getAsset() {
        return asset;
    }
}
