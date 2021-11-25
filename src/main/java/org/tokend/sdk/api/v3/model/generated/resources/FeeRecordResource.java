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
    
    @JsonIgnore
    @Relationship("account")
    private AccountResource account;
    
    public AccountResource getAccount() {
        return account;
    }
    
    @JsonIgnore
    @Relationship("account_role")
    private AccountRoleResource accountRole;
    
    public AccountRoleResource getAccountRole() {
        return accountRole;
    }
    
    @JsonIgnore
    @Relationship("asset")
    private AccountResource asset;
    
    public AccountResource getAsset() {
        return asset;
    }
}
